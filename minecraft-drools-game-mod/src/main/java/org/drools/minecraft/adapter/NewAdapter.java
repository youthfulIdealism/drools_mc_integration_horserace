/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.minecraft.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.game.core.api.GameSession;
import org.drools.game.core.*;
import org.drools.game.core.api.GameConfiguration;
import org.drools.game.core.api.Command;
import org.drools.game.core.api.Context;
import org.drools.game.core.api.PlayerConfiguration;
import org.drools.game.model.api.Player;
import org.drools.game.model.impl.base.BasePlayerImpl;
import org.drools.game.horserace.cmds.*;
import org.drools.game.horserace.model.*;
import org.drools.minecraft.adapter.cmds.ChangeScoreCommand;
import org.kie.api.runtime.rule.FactHandle;

public class NewAdapter
{

    private static final NewAdapter INSTANCE = new NewAdapter();

    private int throttle = 0;
    private final int maxThrottle = 20;
    private boolean hasSetUpWorld;

    private GameSession game;

    public NewAdapter()
    {
        game = new GameSessionImpl();
        game.setExecutor(new CommandExecutorImpl());
        game.setMessageService(new GameMessageServiceImpl());
        game.setCallbackService(new GameCallbackServiceImpl());

        CommandRegistry.set("NOTIFY_VIA_CHAT_CALLBACK", "org.drools.minecraft.adapter.cmds.NotifyViaChatCommand");
        CommandRegistry.set("NOTIFY_ALL_VIA_CHAT_CALLBACK", "org.drools.minecraft.adapter.cmds.NotifyAllViaChatCommand");
        CommandRegistry.set("CHANGE_SCORE_CALLBACK", "org.drools.minecraft.adapter.cmds.ChangeScoreCommand");
        bootstrapWorld();

    }

    private void bootstrapWorld()
    {
        List initFacts = new ArrayList();

        Checkpoint startfinish = new Checkpoint("StartFinish", 0, -143, 75, 248, -140, 79, 262, true);
        initFacts.add(startfinish);

        Checkpoint checkpointone = new Checkpoint("CheckPointOne", 1, -143, 75, 292, -134, 82, 295);
        initFacts.add(checkpointone);

        Checkpoint checkpointtwo = new Checkpoint("CheckPointTwo", 2, -107, 75, 295, -104, 82, 286);
        initFacts.add(checkpointtwo);

        Checkpoint checkpointthree = new Checkpoint("CheckPointThree", 3, -104, 75, 218, -113, 81, 215);
        initFacts.add(checkpointthree);

        Checkpoint checkpointfour = new Checkpoint("CheckPointFour", 4, -134, 75, 218, -143, 85, 215);
        initFacts.add(checkpointfour);

        GameConfiguration config = new BaseGameConfigurationImpl(initFacts, "");
        game.bootstrap(config);

        hasSetUpWorld = false;
    }

    public static NewAdapter getInstance()
    {
        return INSTANCE;
    }

    private void update(World world) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        if (!hasSetUpWorld)
        {
            if (world.isAreaLoaded(ChangeScoreCommand.startingpos, ChangeScoreCommand.startingpos.add(0, 23, 40)))
            {
                if (ChangeScoreCommand.useTickMarks)
                {
                    for (int i = 0; i < 20; i++)
                    {
                        for (int a = 0; a < 3 + i; a++)
                        {
                            world.setBlockToAir(ChangeScoreCommand.startingpos.add(0, a, i * 2));

                        }
                    }
                } else
                {
                    for (int i = 2; i >= 0; i--)
                    {
                        for (int p = 0; p < 15; p++)
                        {
                            world.setBlockToAir(ChangeScoreCommand.startingpos.add(0, -(p / 3), (3 - i) * 4 + (3 - p % 3)));
                        }
                    }
                }
                hasSetUpWorld = true;
            }
        }

        for (String player : game.getPlayers())
        {
            EntityPlayer playerEntity = world.getPlayerEntityByName(player);

            Location location = new Location(playerEntity.getPosition().getX(),
                    playerEntity.getPosition().getY(),
                    playerEntity.getPosition().getZ());

            Collection<Checkpoint> checkpoints = game.getGameObjects(Checkpoint.class);
            for (Checkpoint checkpoint : checkpoints)
            {
                if (UtilMathHelper.playerWithinCheckpoint(location, checkpoint) && !checkpoint.getPlayers().contains(player))
                {
                    game.execute(new EnterCheckpointCommand(game.getPlayerByName(player), checkpoint));
                } else if (!UtilMathHelper.playerWithinCheckpoint(location, checkpoint) && checkpoint.getPlayers().contains(player))
                {
                    game.execute(new LeaveCheckpointCommand(game.getPlayerByName(player), checkpoint));
                }
            }

        }
        dealWithCallbacks(world);
    }

    /**
     * Execute any updates that occur when the game ticks.
     *
     * @param event
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        if (!event.world.isRemote)
        {
            if (event.phase == TickEvent.WorldTickEvent.Phase.START)
            {
                return;
            }

            throttle++;
            if (throttle >= maxThrottle)
            {
                throttle = 0;

                //for simplicity's sake, this locks the adapter into only working
                //in the default dimension. Rules will not work in the nether or end.
                //We should change this at some point.
                if (event.world.provider.getDimension() == 0)
                {
                    update(event.world);
                }
            }
        }
    }

    /**
     * Set up player session, inventory, etc.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event)
    {
        if (!event.getWorld().isRemote)
        {
            if (event.getEntity() instanceof EntityPlayer)
            {
                PlayerConfiguration playerConfig = new BasePlayerConfigurationImpl(null);
                String name = event.getEntity().getDisplayName().getUnformattedText();
                Player player = new BasePlayerImpl(name);
                game.join(player, playerConfig);
            }
        }
    }

    /**
     * When the player dies, remove him from any occupied rooms.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerDie(LivingDeathEvent event)
    {
        if (!event.getEntity().worldObj.isRemote)
        {
            if (event.getEntity() instanceof EntityPlayer)
            {
                String name = event.getEntity().getDisplayName().getUnformattedText();
                game.drop(game.getPlayerByName(name));
            }
        }
    }

    private void dealWithCallbacks(World world)
    {
        Context callbackCtx = new ContextImpl();
        callbackCtx.getData().put("world", world);
        Queue<Command> callbacks = game.getCallbacks();
        while (callbacks.peek() != null)
        {
            Command cmd = callbacks.poll();
            cmd.execute(callbackCtx);
        }
    }
}
