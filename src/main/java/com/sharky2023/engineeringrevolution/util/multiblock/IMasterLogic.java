package com.sharky2023.engineeringrevolution.util.multiblock;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface IMasterLogic {
    private BlockEntity self() {
        return (BlockEntity) this;
    }

    default BlockState getMasterBlock() {
        return self().getBlockState(); }

    default BlockPos getMasterPos() {
        return self().getBlockPos();
    }

    default int getMasterXPos(){
        return self().getBlockPos().getX();}

    default int getMasterYPos(){
        return self().getBlockPos().getY();}

    default int getMasterZPos(){
        return self().getBlockPos().getZ();}

    void notifyChange(ISlaveLogic slave, BlockPos pos, BlockState state);
}