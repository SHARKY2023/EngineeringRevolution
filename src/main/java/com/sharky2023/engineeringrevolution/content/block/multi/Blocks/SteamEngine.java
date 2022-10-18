package com.sharky2023.engineeringrevolution.content.block.multi.Blocks;

/*
public class SteamEngine extends BaseMultiBlock implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SteamEngine(Properties properties) {
        super(properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SteamEngineBE(pos, state);
    }


        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
        {
            BlockEntity te = level.getBlockEntity(pos);
            if(!(te instanceof SteamEngineBE sebe))
                return Shapes.block();

            BlockPos diff = (sebe.getMasterOffset());
            return Shapes.box(diff.getX() - 1, diff.getY() - 1, diff.getZ() - 1, 2+diff.getX(), 4+diff.getY(), 2+diff.getZ());
        }

        @Override
        public void playerWillDestroy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
            super.playerWillDestroy(level, pos, state, player);
            BlockEntity be = level.getBlockEntity(pos);
            if (!level.isClientSide() && !(player instanceof FakePlayer) && be instanceof SteamEngineBE sebe) {


                if (!sebe.hasMaster() || !sebe.checkForMaster())
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    popResource(level, pos, new ItemStack(ModBlocks.STEAM_ENGINE_BLOCK.get()));
                    MultiBlockUtil.removeStructureSE(sebe.getMasterPosition(), level);
            return;

        }}
    */ /*
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
       // builder.add(WATERLOGGED);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
                //.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }
}
*/