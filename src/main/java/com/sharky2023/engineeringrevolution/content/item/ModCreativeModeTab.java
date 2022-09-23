package com.sharky2023.engineeringrevolution.content.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ENGINEERINGREVOLUTION_TAB = new CreativeModeTab("engineeringrevolutiontab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZIRCON.get());
        }
    };

}
