package com.sharky2023.engineeringrevolution.api;

import com.sharky2023.engineeringrevolution.api.MultiBlock.IMultiblock;
import net.minecraft.resources.ResourceLocation;


import java.util.HashMap;
import java.util.Map;

public class EngineeringRevolutionAPI {

    public static final String MOD_ID = "engineeringrevolution";

    public static final Map<ResourceLocation, IMultiblock> MULTIBLOCKS = new HashMap<>();

    private static final IInternalHooks INSTANCE;

    static {
        try {
            INSTANCE = (IInternalHooks) Class.forName("com.sharky2023.engineeringrevolution.InternalHooks").getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IInternalHooks instance() {
        return INSTANCE;
    }
    public interface IInternalHooks {


    IMultiblock createMultiblock(ResourceLocation name, String[][] pattern, Object... rawMatchers);













}}
