package com.sharky2023.engineeringrevolution.api;

import com.sharky2023.engineeringrevolution.api.energy.IEnergyStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;

import static net.minecraftforge.common.capabilities.CapabilityManager.get;

public class EngineeringRevolutionCapabilities {

    public static final Capability<IEnergyStorage> ENERGY = get(new CapabilityToken<>(){});
}
