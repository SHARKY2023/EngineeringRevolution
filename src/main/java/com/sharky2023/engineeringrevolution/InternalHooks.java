package com.sharky2023.engineeringrevolution;

import com.sharky2023.engineeringrevolution.api.EngineeringRevolutionAPI;
import com.sharky2023.engineeringrevolution.api.MultiBlock.IMultiblock;
import net.minecraft.resources.ResourceLocation;

public class InternalHooks implements EngineeringRevolutionAPI.IInternalHooks {

    @Override
    public IMultiblock createMultiblock(ResourceLocation name, String[][] pattern, Object... rawMatchers) {
        return null;
    }
}
