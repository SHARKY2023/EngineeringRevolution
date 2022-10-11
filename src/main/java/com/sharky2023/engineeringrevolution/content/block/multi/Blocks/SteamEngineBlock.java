package com.sharky2023.engineeringrevolution.content.block.multi.Blocks;

import com.sharky2023.engineeringrevolution.content.block.multi.MultiBlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


import javax.annotation.Nullable;


public class SteamEngineBlock extends BaseMultiBlock {

    public SteamEngineBlock(Properties properties) {
        super(properties);

    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state2, boolean idk)
    {
        super.onPlace(state, level, pos, state2, idk);
        if(level.isClientSide() || (MultiBlockUtil.isBlockUnbreakable(level, pos.offset(0, 1, 0))) ) {
            return;
        }
        MultiBlockUtil.setupStructureSE(pos.offset(-1, 0, -1), level, true);

    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

 //   private boolean canBeReplaced(Level level, BlockPos pos) {
 //       return level.getBlockState(pos).getBlock().can;
    //  }

}


