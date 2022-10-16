package com.sharky2023.engineeringrevolution.util.multiblock;

import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


import javax.annotation.Nullable;

public class SlaveBE extends BlockEntity implements ISlaveLogic {
    private static final String MASTER_POS = "masterPos";
    private static final String MASTER_BLOCK = "masterBlock";


    public SlaveBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public boolean hasMaster(){
        return masterPos != null;
    }

    protected void setMaster(@Nullable BlockPos master,@Nullable Block block) {
        masterPos = master;
        masterBlock = block;
        this.setChanged();
    }

    @Getter
    @Nullable
    private BlockPos masterPos;
    @Nullable
    private Block masterBlock;

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
