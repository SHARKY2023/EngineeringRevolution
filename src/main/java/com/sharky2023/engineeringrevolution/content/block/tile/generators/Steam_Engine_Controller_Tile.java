package com.sharky2023.engineeringrevolution.content.block.tile.generators;

import com.sharky2023.engineeringrevolution.api.EngineeringRevolutionCapabilities;
import com.sharky2023.engineeringrevolution.api.energy.IEnergyStorage;
import com.sharky2023.engineeringrevolution.content.block.multi.v1.MultiBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import com.sharky2023.engineeringrevolution.util.energy.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicLong;


public class Steam_Engine_Controller_Tile extends BlockEntity /*implements IEnergyStorage*/ {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final CustomEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    int counter;
    private BlockEntity master;



    public Steam_Engine_Controller_Tile(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEAM_ENGINE_CONTROLLER.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    public void tickServer() {
        if (!this.level.isClientSide) {
                if (!MultiBlocks.STEAM_ENGINE.isComplete(this.level, this.worldPosition)) {
                    generate();
                    sendOutPower();
                }
                return;
        }
        return;}

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

    private void sendOutPower() {
        AtomicLong capacity = new AtomicLong(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity be = level.getBlockEntity(worldPosition.relative(direction));
                if (be != null) {
                    boolean doContinue = be.getCapability(EngineeringRevolutionCapabilities.ENERGY, direction.getOpposite()).map(handler -> {
                                if (handler.canReceive()) {
                                    long received = handler.receiveEnergy(Math.min(capacity.get(), 3000), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    private void generate(){
        if (counter > 0) {
            energyStorage.addEnergy(300);
            counter--;
            setChanged();
        }
        if (counter <= 0) {
            ItemStack stack = itemHandler.getStackInSlot(0);
            int burnTime = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
            if (burnTime > 0) {
                itemHandler.extractItem(0, 1, false);
                counter = burnTime;
                setChanged();
            }
        }
    }


    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")) {
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("Info")) {
            counter = tag.getCompound("Info").getInt("Counter");
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", itemHandler.serializeNBT());
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Counter", counter);
        tag.put("Info", infoTag);
    }
    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(60000, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }


}




