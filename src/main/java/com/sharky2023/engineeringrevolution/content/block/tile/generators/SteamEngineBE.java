package com.sharky2023.engineeringrevolution.content.block.tile.generators;

import joptsimple.internal.Rows;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class SteamEngineBE extends BlockEntity {

    private double x = this.getBlockPos().getX();
    private double y = this.getBlockPos().getY();
    private double z = this.getBlockPos().getZ();
    private boolean hasMaster, isMaster;
    private BlockPos masterPos = new BlockPos(0, 0, 0);


    public SteamEngineBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(x-1,y,z,x+1,y+4,z+2);
    }

    public void reset()
    {
        masterPos = new BlockPos(0, 0, 0);
        hasMaster = false;
        isMaster = false;
    }
    public boolean checkForMaster()
    {
        BlockEntity tile = level.getBlockEntity(this.worldPosition.offset(masterPos));
        return (tile instanceof SteamEngineBE);
    }
    @Override
    public void saveAdditional(CompoundTag data)
    {
        super.saveAdditional(data);
        data.putInt("masterX", masterPos.getX());
        data.putInt("masterY", masterPos.getY());
        data.putInt("masterZ", masterPos.getZ());
        data.putBoolean("hasMaster", hasMaster);
        data.putBoolean("isMaster", isMaster);
    }

    @Override
    public void load(CompoundTag data)
    {
        super.load(data);
        int masterX = data.getInt("masterX");
        int masterY = data.getInt("masterY");
        int masterZ = data.getInt("masterZ");
        this.masterPos = new BlockPos(masterX, masterY, masterZ);
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
    }
    public boolean hasMaster()
    {
        return hasMaster;
    }

    public boolean isMaster()
    {
        return isMaster;
    }

    public BlockPos getMasterPostion()
    {
        return this.worldPosition.offset(masterPos);
    }

    public void setHasMaster(boolean bool)
    {
        hasMaster = bool;
    }

    public void setIsMaster(boolean bool)
    {
        isMaster = bool;
    }

    public void setMasterCoords(int x, int y, int z)
    {
        this.setMasterCoords(new BlockPos(x, y, z));
    }

    public void setMasterCoords(BlockPos pos)
    {
        this.masterPos = pos.subtract(this.worldPosition);
    }

}
