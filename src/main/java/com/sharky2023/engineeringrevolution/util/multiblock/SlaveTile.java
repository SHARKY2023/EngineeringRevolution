package com.sharky2023.engineeringrevolution.util.multiblock;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


import javax.annotation.Nullable;

public class SlaveTile extends BlockEntity implements ISlaveLogic {

    public SlaveTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Nullable
    @Override
    public BlockPos getMasterPos() {
        return null;
    }

    @Override
    public void notifyMasterOfChange(BlockPos Pos, BlockState state) {

    }

    @Override
    public boolean isValidMaster(IMasterLogic master) {
        return false;
    }

    @Override
    public void setPotentialMaster(IMasterLogic master) {

    }

    @Override
    public void removeMaster(IMasterLogic master) {

    }
}