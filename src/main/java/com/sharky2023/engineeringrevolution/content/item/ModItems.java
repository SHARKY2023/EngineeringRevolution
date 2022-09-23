package com.sharky2023.engineeringrevolution.content.item;

import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EngineeringRevolution.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB)));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB)));
    public static final RegistryObject<Item> PICKAXE_ZIRCON = ITEMS.register("pickaxe_zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENGINEERINGREVOLUTION_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
