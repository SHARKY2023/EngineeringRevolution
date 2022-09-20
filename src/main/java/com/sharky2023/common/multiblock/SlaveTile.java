package com.sharky2023.common.multiblock;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


import javax.annotation.Nullable;

public class SlaveTile extends BlockEntity implements ISlaveLogic {


    @Nullable
    private BlockPos masterPos;
    @Nullable
    private Block masterBlock;

    public SlaveTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    public boolean hasMaster(){
        return masterPos != null;
    }

    protected void setMaster(@Nullable BlockPos master, @Nullable Block block) {
        masterPos = master;
        masterBlock = block;
        this.setChangedFast();
    }





}