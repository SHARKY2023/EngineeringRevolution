package com.sharky2023.engineeringrevolution.content.block.tile;


import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
//import com.sharky2023.engineeringrevolution.content.block.tile.generators.Steam_Engine_Controller_Tile;
import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
//import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
import com.sharky2023.engineeringrevolution.content.block.tile.machine.BlastFurnaceBE;
import com.sharky2023.engineeringrevolution.content.block.tile.machine.ElectricFurnaceBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EngineeringRevolution.MOD_ID);


    public static final RegistryObject<BlockEntityType<SteamEngineBE>> STEAMENGINE_BE =
            BLOCK_ENTITIES.register("steamengine", () -> BlockEntityType.Builder.of(SteamEngineBE::new,
                    ModBlocks.STEAMENGINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlastFurnaceBE>> BLASTFURNACE_BE =
            BLOCK_ENTITIES.register("blastfurnace", () -> BlockEntityType.Builder.of(BlastFurnaceBE::new,
                    ModBlocks.BLASTFURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectricFurnaceBE>> ELECTRICFURNACE_BE =
            BLOCK_ENTITIES.register("electric_furnace", () -> BlockEntityType.Builder.of(ElectricFurnaceBE::new,
                    ModBlocks.ELECTRICFURNACE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
