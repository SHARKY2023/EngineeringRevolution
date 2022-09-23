package com.sharky2023.engineeringrevolution.util.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

import javax.annotation.Nullable;

public interface ISlaveLogic extends IForgeBlockEntity {
    @Nullable
    BlockPos getMasterPos();
    void notifyMasterOfChange(BlockPos Pos, BlockState state);
    boolean isValidMaster(IMasterLogic master);
    void setPotentialMaster(IMasterLogic master);
    void removeMaster(IMasterLogic master);

}
