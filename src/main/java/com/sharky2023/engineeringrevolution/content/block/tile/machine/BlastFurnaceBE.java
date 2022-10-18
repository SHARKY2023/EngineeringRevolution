package com.sharky2023.engineeringrevolution.content.block.tile.machine;

import com.sharky2023.engineeringrevolution.EngineeringRevolution;
import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import com.sharky2023.engineeringrevolution.content.menu.BlastFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlastFurnaceBE extends BlockEntity implements MenuProvider {


    public BlastFurnaceBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLASTFURNACE_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Blast Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BlastFurnaceMenu(id,inventory,this);
    }

    public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
    }

    public void drops()
    {
   //     SimpleContainer Inventory = new SimpleContainer(IItemHandler.getSlots());
   //     for(int i = 0; i < IItemHandler.getSlots(); i ++)
   //     {
   //         Inventory.setItem(i, IItemHandler.getStackInSlot(i));
   //     }



    //    Containers.dropContents(this.level, this.worldPosition, Inventory);
    }
}
