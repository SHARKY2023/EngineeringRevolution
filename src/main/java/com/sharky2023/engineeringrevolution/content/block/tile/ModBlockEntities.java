package com.sharky2023.engineeringrevolution.content.block.tile;


import com.sharky2023.engineeringrevolution.content.block.blocks.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.Steam_Engine_Controller_Tile;
import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EngineeringRevolution.MOD_ID);

    public static final RegistryObject<BlockEntityType<Steam_Engine_Controller_Tile>> STEAMENGINE =
            BLOCK_ENTITIES.register("steam_engine", () ->
                    BlockEntityType.Builder.of(Steam_Engine_Controller_Tile::new,
                            ModBlocks.STEAM_ENGINE_CONTROLLER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
