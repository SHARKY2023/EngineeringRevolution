package com.sharky2023.engineeringrevolution.util.energy;

import com.sharky2023.engineeringrevolution.api.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

    public CustomEnergyStorage(long capacity, long maxTransfer) {
        super(capacity, maxTransfer, 0);
    }

    protected void onEnergyChanged() {
    }

    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        long rc = super.receiveEnergy(maxReceive, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        long rc = super.extractEnergy(maxExtract, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(long energy) {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(long energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }
}