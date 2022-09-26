package com.sharky2023.engineeringrevolution.api.MultiBlock.v2;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.TriPredicate;

public interface StateMatcher {

    ResourceLocation getType();

    BlockState getDisplayedState(long ticks);

    TriPredicate<BlockGetter, BlockPos, BlockState> getStatePredicate();

    void toNetwork(FriendlyByteBuf buffer);





}
