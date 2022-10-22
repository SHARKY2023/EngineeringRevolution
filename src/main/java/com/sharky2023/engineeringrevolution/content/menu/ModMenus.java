package com.sharky2023.engineeringrevolution.content.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sharky2023.engineeringrevolution.EngineeringRevolution.MOD_ID;

public class ModMenus {


    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);


    public static final RegistryObject<MenuType<SteamEngineMenu>> STEAMENGINE_MENU =
            registerMenuType(SteamEngineMenu::new,"steamengine");
    public static final RegistryObject<MenuType<BlastFurnaceMenu>> BLASTFURNACE_MENU =
            registerMenuType(BlastFurnaceMenu::new,"blastfurnace");
    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRICFURNACE_MENU =
            registerMenuType(ElectricFurnaceMenu::new,"eletric_furnace");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register()
    {
        MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}