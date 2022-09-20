package com.sharky2023.common.block.tile.generators;

import com.sharky2023.common.block.tile.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class Steam_Engine_Controller_Tile extends BlockEntity {
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public Steam_Engine_Controller_Tile(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEAM_ENGINE_CONTROLLER.get(), pos, state);


    }

    public static <E extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, E e) {
    }
    // @Override
   // public BlockEntity createTileEntity(BlockState state, IBlockReader world) {return new BlockEntity() {
   // };}






}
