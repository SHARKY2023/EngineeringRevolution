package com.sharky2023.engineeringrevolution.content.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sharky2023.engineeringrevolution.EngineeringRevolution.MOD_ID;

public class ModContainers {

    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);

    public static final RegistryObject<MenuType<SteamEngineContainer>> STEAMENGINE_CONTAINER = CONTAINERS.register("steamengine",
            () -> IForgeMenuType.create((windowId, inv, data) -> new SteamEngineContainer(windowId, data.readBlockPos(), inv, inv.player)));







    public static void register(IEventBus EventBus) {CONTAINERS.register(EventBus);
    }
}
