package com.sharky2023.engineeringrevolution.content.block.blocks;

import com.sharky2023.engineeringrevolution.content.block.tile.machine.ElectricFurnaceBE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class ElectricFurnace extends BaseEntityBlock {


    public ElectricFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState BState, Level Level, BlockPos BPos, Player player, InteractionHand Hand, BlockHitResult Result) {
        if(!Level.isClientSide) {
            BlockEntity entity = Level.getBlockEntity(BPos);
            if(entity instanceof ElectricFurnaceBE) {
                NetworkHooks.openScreen(((ServerPlayer)player), (ElectricFurnaceBE)entity, BPos); }
            else {
                throw new IllegalStateException("Container provider is missing!"); }
        }
        return InteractionResult.sidedSuccess(Level.isClientSide());
    }

    @Override
    public void onRemove(BlockState BState, Level Level, BlockPos BPos, BlockState BState2, boolean bool) {
        if(BState.getBlock() != BState2.getBlock())
        {
            BlockEntity blockEntity = Level.getBlockEntity(BPos);
            if(blockEntity instanceof ElectricFurnaceBE)
            {
                ((ElectricFurnaceBE) blockEntity).drops();
            }
        }
        super.onRemove(BState, Level, BPos, BState2, bool);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos state, BlockState pos) {
        return new ElectricFurnaceBE(state, pos);
    }

}
