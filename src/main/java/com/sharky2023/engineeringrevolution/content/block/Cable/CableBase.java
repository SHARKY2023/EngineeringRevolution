package com.sharky2023.engineeringrevolution.content.block.Cable;

import com.google.common.collect.Maps;
import com.sharky2023.engineeringrevolution.content.block.blocks.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.ModBlockEntities;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class CableBase extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final EnumProperty<ConnectionType> DOWN = EnumProperty.create("down", ConnectionType.class);
    public static final EnumProperty<ConnectionType> UP = EnumProperty.create("up", ConnectionType.class);
    public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
    public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
    public static final EnumProperty<ConnectionType> WEST = EnumProperty.create("west", ConnectionType.class);
    public static final EnumProperty<ConnectionType> EAST = EnumProperty.create("east", ConnectionType.class);
    public static final Map<Direction, EnumProperty<ConnectionType>> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p) -> {
        p.put(Direction.NORTH, NORTH);
        p.put(Direction.EAST, EAST);
        p.put(Direction.SOUTH, SOUTH);
        p.put(Direction.WEST, WEST);
        p.put(Direction.UP, UP);
        p.put(Direction.DOWN, DOWN);
    });

    protected static final VoxelShape SHAPE = Block.box(6, 6, 6, 10, 10, 10);
    //Y for updown
    protected static final VoxelShape SHAPE_UP = Block.box(6, 6, 6, 10, 16, 10);
    protected static final VoxelShape SHAPE_DOWN = Block.box(6, 0, 6, 10, 10, 10);
    //Z for n-s
    protected static final VoxelShape SHAPE_NORTH = Block.box(6, 6, 0, 10, 10, 10);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(6, 6, 6, 10, 10, 16);
    //X for e-w
    protected static final VoxelShape SHAPE_WEST = Block.box(0, 6, 6, 10, 10, 10);
    protected static final VoxelShape SHAPE_EAST = Block.box(6, 6, 6, 16, 10, 10);

    static boolean shapeConnects(BlockState state, EnumProperty<ConnectionType> dirctionProperty) {
        return state.getValue(dirctionProperty).isConnected();
    }
    public static VoxelShape createShape(BlockState state) {
        VoxelShape shape = SHAPE;
        if (shapeConnects(state, UP)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_UP, BooleanOp.OR);
        }
        if (shapeConnects(state, DOWN)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_DOWN, BooleanOp.OR);
        }
        if (shapeConnects(state, WEST)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_WEST, BooleanOp.OR);
        }
        if (shapeConnects(state, EAST)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_EAST, BooleanOp.OR);
        }
        if (shapeConnects(state, NORTH)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_NORTH, BooleanOp.OR);
        }
        if (shapeConnects(state, SOUTH)) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_SOUTH, BooleanOp.OR);
        }
        return shape;
    }

    public CableBase(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (hit.getDirection() == null) {
            return super.use(state, world, pos, player, handIn, hit);
        }
        if (handIn != InteractionHand.MAIN_HAND) {
            return super.use(state, world, pos, player, handIn, hit);
        }
        if (!world.isClientSide) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MenuProvider) {
                NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider) tileEntity, tileEntity.getBlockPos());
                    }
                    else {
                        throw new IllegalStateException("Our named container provider is missing!");
                    }
                }
                return InteractionResult.SUCCESS;
            }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

/*    EnumProperty<ConnectionType> property = CableBase.FACING_TO_PROPERTY_MAP.get(side);
    return blockState.getBlock() instanceof CableBase
        && blockState.hasProperty(property)
            && blockState.getValue(property).isUnBlocked() == false;
*/


}
