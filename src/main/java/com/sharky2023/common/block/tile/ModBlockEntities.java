package com.sharky2023.common.block.tile;


import com.sharky2023.common.block.ModBlocks;
import com.sharky2023.common.block.tile.generators.Steam_Engine_Controller_Tile;
import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EngineeringRevolution.MOD_ID);

    public static final RegistryObject<BlockEntityType<Steam_Engine_Controller_Tile>> STEAM_ENGINE_CONTROLLER =
            BLOCK_ENTITIES.register("gem_infusing_station", () ->
                    BlockEntityType.Builder.of(Steam_Engine_Controller_Tile::new,
                            ModBlocks.STEAM_ENGINE_CONTROLLER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
