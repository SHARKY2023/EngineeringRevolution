package com.sharky2023.common.multiblock;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface IMasterLogic {
    private BlockEntity self() {
        return (BlockEntity) this;
    }
    default BlockState getMasterBlock() {
        return self().getBlockState(); }
    default BlockPos getMasterPos() {
        return self().getBlockPos();}
    void notifyChange(ISlaveLogic slave, BlockPos pos, BlockState state);
}