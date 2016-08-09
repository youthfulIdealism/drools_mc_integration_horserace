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

public class ChangeScoreCommand extends BaseCommand<Void> {
    public  static final BlockPos startingpos  = new BlockPos(-143, 85, 248);
        public static final boolean useTickMarks = false;
    
    public static final boolean[] numeral0 = new boolean[]
                                           { true, true, true,
                                             true, false, true,
                                             true, false, true,
                                             true, false, true,
                                             true, true, true};
    public static final boolean[] numeral1 = new boolean[]
                                           { false, false, true,
                                             false, false, true,
                                             false, false, true,
                                             false, false, true,
                                             false, false, true};
    public static final boolean[] numeral2 = new boolean[]
                                           { true, true, true,
                                             false, false, true,
                                             true, true, true,
                                             true, false, false,
                                             true, true, true};
    public static final boolean[] numeral3 = new boolean[]
                                           { true, true, true,
                                             false, false, true,
                                             true, true, true,
                                             false, false, true,
                                             true, true, true};
    public static final boolean[] numeral4 = new boolean[]
                                           { true, false, true,
                                             true, false, true,
                                             true, true, true,
                                             false, false, true,
                                             false, false, true};
    public static final boolean[] numeral5 = new boolean[]
                                           { true, true, true,
                                             true, false, false,
                                             true, true, true,
                                             false, false, true,
                                             true, true, true};
    public static final boolean[] numeral6 = new boolean[]
                                           { true, true, true,
                                             true, false, false,
                                             true, true, true,
                                             true, false, true,
                                             true, true, true};
    public static final boolean[] numeral7 = new boolean[]
                                           { true, true, true,
                                             false, false, true,
                                             false, false, true,
                                             false, false, true,
                                             false, false, true};
    public static final boolean[] numeral8 = new boolean[]
                                           { true, true, true,
                                             true, false, true,
                                             true, true, true,
                                             true, false, true,
                                             true, true, true};
    public static final boolean[] numeral9 = new boolean[]
                                           { true, true, true,
                                             true, false, true,
                                             true, true, true,
                                             false, false, true,
                                             false, false, true};
    
    
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
        
        if(useTickMarks)
        {
            for(int i = 0; i < 3 + amount; i++)
            {
                world.setBlockState(startingpos.add(0, i, amount * 2), blockstate);
            }
        }else
        {
            char[] scoreNumerals = (amount + "").toCharArray();
            
            for(int i = scoreNumerals.length - 1; i >= 0; i--)
            {
                char digit = scoreNumerals[i];
                boolean[] digitmap = null;
                if(digit == '0')
                {
                    digitmap = numeral0;
                }else if(digit == '1')
                {
                    digitmap = numeral1;
                }else if(digit == '2')
                {
                    digitmap = numeral2;
                }else if(digit == '3')
                {
                    digitmap = numeral3;
                }else if(digit == '4')
                {
                    digitmap = numeral4;
                }else if(digit == '5')
                {
                    digitmap = numeral5;
                }else if(digit == '6')
                {
                    digitmap = numeral6;
                }else if(digit == '7')
                {
                    digitmap = numeral7;
                }else if(digit == '8')
                {
                    digitmap = numeral8;
                }else if(digit == '9')
                {
                    digitmap = numeral9;
                }
                
                for(int p = 0; p < digitmap.length; p++)
                {
                    IBlockState chosen = null;
                    if(digitmap[p])
                    {
                        chosen = blockstate;
                    }else
                    {
                        chosen = airstate;
                    }
                    world.setBlockState(startingpos.add(0, -(p / 3), (scoreNumerals.length - i) * 4 + (3 - p % 3)), chosen);
                }
            }
        }
        
        return null;

    }

}
