package com.sharky2023.engineeringrevolution.content.container;

import com.sharky2023.engineeringrevolution.api.EngineeringRevolutionCapabilities;
import com.sharky2023.engineeringrevolution.api.energy.IEnergyStorage;
import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
import com.sharky2023.engineeringrevolution.util.energy.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SteamEngineContainer extends AbstractContainerMenu {

    //public SteamEngineControllerBE tile;
        private BlockEntity BlockEntity;
        private Player playerEntity;
        private IItemHandler playerInventory;

    public SteamEngineContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
            super(ModContainers.STEAMENGINE_CONTAINER.get(), windowId);
            BlockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
            this.playerEntity = player;
            this.playerInventory = new InvWrapper(playerInventory);

            if (BlockEntity != null) {
                BlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 64, 24)));
            }
            layoutPlayerInventorySlots(10, 70);
            trackPower();
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
                    BlockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).ifPresent(h -> {
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
                    BlockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).ifPresent(h -> {
                        long energyStored = h.getEnergyStored() & 0x0000ffff;
                        ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                    });
                }
            });
        }

        public Long getEnergy() {
            return BlockEntity.getCapability(EngineeringRevolutionCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse((long) 0);
        }

        @Override
        public boolean stillValid(Player playerIn) {
            return stillValid(ContainerLevelAccess.create(BlockEntity.getLevel(), BlockEntity.getBlockPos()), playerEntity, ModBlocks.STEAMENGINECONTROLLER.get());
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
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
                    if (stack.getItem() == Items.COAL) {
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

                slot.onTake(playerIn, stack);
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
