package com.sharky2023.engineeringrevolution.Client;


import com.sharky2023.engineeringrevolution.content.menu.ModMenus;
import com.sharky2023.engineeringrevolution.gui.machine.BlastFurnaceScreen;
import com.sharky2023.engineeringrevolution.gui.machine.SteamEngineScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import static com.sharky2023.engineeringrevolution.EngineeringRevolution.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientSetup {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent Event)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MenuScreens.register(ModMenus.BLASTFURNACE_MENU.get(), BlastFurnaceScreen::new);
                MenuScreens.register(ModMenus.STEAMENGINE_MENU.get(), SteamEngineScreen::new);
            }
        };
        Event.enqueueWork(runnable);
    }



}
