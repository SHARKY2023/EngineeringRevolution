package com.sharky2023.engineeringrevolution.content.block.tile.machine;

import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlastFurnaceBE extends BlockEntity {

    public BlastFurnaceBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLASTFURNACE.get(), pos, state);
    }
}
