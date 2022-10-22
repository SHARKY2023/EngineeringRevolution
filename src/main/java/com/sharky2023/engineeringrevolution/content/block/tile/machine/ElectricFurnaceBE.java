package com.sharky2023.engineeringrevolution.content.block.tile.machine;

import com.sharky2023.engineeringrevolution.api.energy.IEnergyStorage;
import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import com.sharky2023.engineeringrevolution.content.menu.BlastFurnaceMenu;
import com.sharky2023.engineeringrevolution.content.menu.ElectricFurnaceMenu;
import com.sharky2023.engineeringrevolution.util.energy.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElectricFurnaceBE extends BlockEntity  implements MenuProvider {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final CustomEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public ElectricFurnaceBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTRICFURNACE_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Electric Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ElectricFurnaceMenu(id,inventory,this);
    }

    public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
    }


    public void serverTick() {
        if (!this.level.isClientSide) {
            if  >
        }

    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) { @Override

        protected void onContentsChanged(int slot) { setChanged(); }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) { return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0; }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };

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

