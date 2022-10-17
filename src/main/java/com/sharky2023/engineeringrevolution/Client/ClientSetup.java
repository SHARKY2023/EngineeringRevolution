package com.sharky2023.engineeringrevolution.Client;


import com.sharky2023.engineeringrevolution.content.container.ModContainers;
import com.sharky2023.engineeringrevolution.content.container.SteamEngineContainer;
import com.sharky2023.engineeringrevolution.gui.machine.SteamEngineScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


import static com.sharky2023.engineeringrevolution.EngineeringRevolution.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModContainers.STEAMENGINE_CONTAINER.get(), SteamEngineScreen::new);

        });
    }


}
