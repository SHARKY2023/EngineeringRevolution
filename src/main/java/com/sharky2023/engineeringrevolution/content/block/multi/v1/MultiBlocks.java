package com.sharky2023.engineeringrevolution.content.block.multi.v1;

import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import com.sharky2023.engineeringrevolution.api.EngineeringRevolutionAPI;
import com.sharky2023.engineeringrevolution.api.MultiBlock.IMultiblock;
import com.sharky2023.engineeringrevolution.content.block.blocks.ModBlocks;
import net.minecraft.resources.ResourceLocation;


public final class MultiBlocks {


    private static final String[][] STEAM_ENGINE_PATTERN = new String[][]{
            {"FFFFF", "FPPPF", "FFFFF"},
            {"FPOPF", "0FFFO", "FFOFF"},
            {"FFFFF", "FFFFF", "FFFFF"}};
    public static final IMultiblock STEAM_ENGINE = EngineeringRevolutionAPI.instance().createMultiblock(
        new ResourceLocation(EngineeringRevolution.MOD_ID, "steam_engine"),
        MultiBlocks.STEAM_ENGINE_PATTERN,
            '0', ModBlocks.STEAM_ENGINE_CONTROLLER,
            'P', ModBlocks.STEAM_ENGINE,
            'F', ModBlocks.STEAM_ENGINE_HOUSING,
            'O', ModBlocks.STEAM_ENGINE_IO
            );
}
