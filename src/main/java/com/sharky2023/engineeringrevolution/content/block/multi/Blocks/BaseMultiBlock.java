package com.sharky2023.engineeringrevolution.content.block.multi.Blocks;

import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BaseMultiBlock extends Block{

    public BaseMultiBlock(Properties builder){
        super(builder);
    }



    protected static Properties getBuilder(){
        return Properties.of(Material.DIRT).strength(0.5f, Float.MAX_VALUE);
    }
}

