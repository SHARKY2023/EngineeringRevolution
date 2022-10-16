package com.sharky2023.engineeringrevolution.content.block.tile;


import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
//import com.sharky2023.engineeringrevolution.content.block.tile.generators.Steam_Engine_Controller_Tile;
import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineControllerBE;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
import com.sharky2023.engineeringrevolution.content.block.tile.machine.BlastFurnaceBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EngineeringRevolution.MOD_ID);

    public static final RegistryObject<BlockEntityType<SteamEngineBE>> STEAMENGINE =
            BLOCK_ENTITIES.register("steam_engine", () ->
                    BlockEntityType.Builder.of(SteamEngineBE::new,
                            ModBlocks.STEAM_ENGINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteamEngineControllerBE>> STEAMENGINECONTROLLER =
            BLOCK_ENTITIES.register("steam_engine_controller", () ->
                    BlockEntityType.Builder.of(SteamEngineControllerBE::new,
                            ModBlocks.STEAM_ENGINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlastFurnaceBE>> BLASTFURNACE =
            BLOCK_ENTITIES.register("steam_engine_controller", () ->
                    BlockEntityType.Builder.of(BlastFurnaceBE::new,
                            ModBlocks.BLASTFURNACE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
