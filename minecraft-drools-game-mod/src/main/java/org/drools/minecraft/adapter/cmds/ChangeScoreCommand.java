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

package org.drools.minecraft.adapter.cmds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.drools.game.core.api.BaseCommand;
import org.drools.game.core.api.Context;
import org.drools.game.model.api.Player;
import org.drools.minecraft.adapter.UtilMathHelper;
/**
 * Command that changes the scoreboard within minecraft to correctly represent the score.
 * @author Samuel
 */
public class ChangeScoreCommand extends BaseCommand<Void> {
    public  static final BlockPos startingpos  = new BlockPos(-143, 85, 248);
    
    
    private int amount;
    
    public ChangeScoreCommand( Player player, int amount) {
        super( player);
        this.amount = amount;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
    @Override
    public Void execute( Context ctx ) {
        World world = ( World ) ctx.getData().get( "world" );
        IBlockState blockstate = Blocks.LAPIS_BLOCK.getDefaultState();
        IBlockState airstate = Blocks.AIR.getDefaultState();
        
        UtilMathHelper.displayScore(world, startingpos, blockstate, amount);
        return null;

    }

}
