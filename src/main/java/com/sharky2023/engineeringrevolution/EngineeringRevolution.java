package com.sharky2023.engineeringrevolution;

import com.mojang.logging.LogUtils;
import com.sharky2023.engineeringrevolution.Client.ClientSetup;
import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import com.sharky2023.engineeringrevolution.content.menu.ModMenus;
import com.sharky2023.engineeringrevolution.content.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(EngineeringRevolution.MOD_ID)
public class EngineeringRevolution
{
    public static final String MOD_ID = "engineeringrevolution";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EngineeringRevolution() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register();


        modEventBus.addListener(ClientSetup::clientSetup);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }


    private void commonSetup(final FMLCommonSetupEvent event) {
    }


/*
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{}


    */

}
