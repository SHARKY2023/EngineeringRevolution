package com.sharky2023.engineeringrevolution.content.menu;

import com.sharky2023.engineeringrevolution.api.EngineeringRevolutionCapabilities;
import com.sharky2023.engineeringrevolution.api.energy.IEnergyStorage;
import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
import com.sharky2023.engineeringrevolution.util.energy.CustomEnergyStorage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SteamEngineMenu extends AbstractContainerMenu {

    private SteamEngineBE blockEntity;
    private final Level level;
    private IItemHandler playerInventory;

    public SteamEngineMenu(int ContainerId, Inventory Inv, FriendlyByteBuf extraData) {
        this(ContainerId, Inv, Inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public SteamEngineMenu(int ContainerId, Inventory Inv, BlockEntity entity) {
        super(ModMenus.STEAMENGINE_MENU.get(), ContainerId);
        checkContainerSize(Inv, 41);
        blockEntity = ((SteamEngineBE) entity);
        this.level = Inv.player.level;

/*
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 64, 24));
            });
        }

        layoutPlayerInventorySlots(10, 70);
        trackPower();

        }
*/
    }


    // Setup syncing of power from server to client so that the GUI can show the amount of power in the block
        private void trackPower() {
            // Unfortunatelly on a dedicated server ints are actually truncated to short so we need
            // to split our integer here (split our 32 bit integer into two 16 bit integers)
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (int) (getEnergy() & 0xffff);
                }

                @Override
                public void set(int value) {
                    blockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).ifPresent(h -> {
                        long energyStored = h.getEnergyStored() & 0xffff0000;
                        ((CustomEnergyStorage)h).setEnergy(energyStored + (value & 0xffff));
                    });
                }
            });
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (int) ((getEnergy() >> 16) & 0xffff);
                }

                @Override
                public void set(int value) {
                    blockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).ifPresent(h -> {
                        long energyStored = h.getEnergyStored() & 0x0000ffff;
                        ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                    });
                }
            });
        }

        public Long getEnergy() {
            return blockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse((long) 0);
        }

        @Override
        public boolean stillValid(Player player) {
            return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.STEAMENGINE.get());
        }

        @Override
        public ItemStack quickMoveStack(Player player, int index) {
            ItemStack itemstack = ItemStack.EMPTY;
            Slot slot = this.slots.get(index);
            if (slot != null && slot.hasItem()) {
                ItemStack stack = slot.getItem();
                itemstack = stack.copy();
                if (index == 0) {
                    if (!this.moveItemStackTo(stack, 1, 37, true)) {
                        return ItemStack.EMPTY;
                    }
                    slot.onQuickCraft(stack, itemstack);
                } else {
                    if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
                        if (!this.moveItemStackTo(stack, 0, 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 28) {
                        if (!this.moveItemStackTo(stack, 28, 37, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                        return ItemStack.EMPTY;
                    }
                }

                if (stack.isEmpty()) {
                    slot.set(ItemStack.EMPTY);
                } else {
                    slot.setChanged();
                }

                if (stack.getCount() == itemstack.getCount()) {
                    return ItemStack.EMPTY;
                }

                slot.onTake(player, stack);
            }

            return itemstack;
        }



        private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
            for (int i = 0 ; i < amount ; i++) {
                addSlot(new SlotItemHandler(handler, index, x, y));
                x += dx;
                index++;
            }
            return index;
        }

        private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
            for (int j = 0 ; j < verAmount ; j++) {
                index = addSlotRange(handler, index, x, y, horAmount, dx);
                y += dy;
            }
            return index;
        }

        private void layoutPlayerInventorySlots(int leftCol, int topRow) {
            // Player inventory
            addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

            // Hotbar
            topRow += 58;
            addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
        }


    }

