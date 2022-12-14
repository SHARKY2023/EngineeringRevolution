package com.sharky2023.engineeringrevolution.content.block;

//import com.sharky2023.engineeringrevolution.content.block.blocks.steam_boiler.Steam_Engine_Controller;
//import com.sharky2023.engineeringrevolution.content.block.multi.Blocks.BaseMultiBlock;
//import com.sharky2023.engineeringrevolution.content.block.multi.Blocks.BlastFurnace;
//import com.sharky2023.engineeringrevolution.content.block.multi.Blocks.SteamEngine;
//import com.sharky2023.engineeringrevolution.content.block.multi.Blocks.SteamEngineBlock;
import com.sharky2023.engineeringrevolution.content.block.blocks.BlastFurnace;
import com.sharky2023.engineeringrevolution.content.block.blocks.ElectricFurnace;
import com.sharky2023.engineeringrevolution.content.block.blocks.SteamEngine;
import com.sharky2023.engineeringrevolution.content.item.ModCreativeModeTab;
import com.sharky2023.engineeringrevolution.content.item.ModItems;
import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EngineeringRevolution.MOD_ID);

    public static final RegistryObject<Block> CRUSHER = registerBlock("crusher",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB);
    public static final RegistryObject<ElectricFurnace> ELECTRICFURNACE = registerBlock("electric_furnace",
            () -> new ElectricFurnace(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB);
    public static final RegistryObject<Block> MACHINE_BLOCK = registerBlock("machine_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB);
    public static final RegistryObject<SteamEngine> STEAMENGINE = registerBlock("steamengine",
            () -> new SteamEngine(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB);
    public static final RegistryObject<BlastFurnace> BLASTFURNACE = registerBlock("blastfurnace",
            () -> new BlastFurnace(BlockBehaviour.Properties.of(Material.METAL)
            .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
