package com.sharky2023.engineeringrevolution.content.block.multi.Blocks;


import com.sharky2023.engineeringrevolution.content.item.ModCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BaseMultiBlock extends Block{

    public BaseMultiBlock(Properties builder){
        super(builder);
    }


    protected static Properties getBuilder(){
        return BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops();
    }
}

