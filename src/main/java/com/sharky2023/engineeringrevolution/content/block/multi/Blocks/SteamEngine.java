package com.sharky2023.engineeringrevolution.content.block.multi.Blocks;

import com.sharky2023.engineeringrevolution.content.block.blocks.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryManager;

public class SteamEngine extends Block {


    public SteamEngine(Properties p_49795_) {
        super(p_49795_);
    }

    public static boolean areAllReplaceable(BlockPos start, BlockPos end, BlockPlaceContext context) {
        Level w = context.getLevel();
        return BlockPos.betweenClosedStream(start, end).allMatch(
                pos -> {
                    BlockPlaceContext subContext = BlockPlaceContext.at(context, pos, context.getClickedFace());
                    return w.getBlockState(pos).canBeReplaced(subContext);
                });

    }
    public static void setupStructure(BlockPos pos, Level level, boolean areCoordsCorrect)
    {
        if(CCubesSettings.disableGiantCC.get())
            return;
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();

        if(!areCoordsCorrect)
        {
            BlockPos bottomLeft = findBottomCorner(pos, level);
            cx = bottomLeft.getX();
            cy = bottomLeft.getY();
            cz = bottomLeft.getZ();
        }

        int i = 0;
        for(int x = cx; x < cx + 3; x++)
        {
            for(int z = cz; z < cz + 3; z++)
            {
                for(int y = cy; y < cy + 3; y++)
                {
                    i++;

                    RewardsUtil.placeBlock(ModBlocks.STEAM_ENGINE.defaultBlockState(), level, new BlockPos(x, y, z), i == 27 ? 3 : 2, level.getBlockState(new BlockPos(x, y, z)).getBlock().equals(CCubesBlocks.CHANCE_CUBE));


                    BlockEntity tile = level.getBlockEntity(new BlockPos(x, y, z));
                    // Check if block is bottom center block
                    boolean master = (x == cx + 1 && y == cy + 1 && z == cz + 1);
                    if(tile instanceof TileGiantCube)
                    {
                        ((TileGiantCube) tile).setMasterCoords(cx + 1, cy + 1, cz + 1);
                        ((TileGiantCube) tile).setHasMaster(true);
                        ((TileGiantCube) tile).setIsMaster(master);
                    }
                }
            }
        }

        public static void removeStructure(BlockPos pos, Level level)
        {
            for(int x = pos.getX() - 1; x < pos.getX() + 2; x++)
                for(int y = pos.getY() - 1; y < pos.getY() + 2; y++)
                    for(int z = pos.getZ() - 1; z < pos.getZ() + 2; z++)
                    {
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockEntity tile = level.getBlockEntity(blockPos);
                        if(tile instanceof SteamEngineBE)
                        {
                            ((SteamEngineBE) tile).reset();
                            level.removeBlockEntity(blockPos);
                            level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                        }
                    }
        }
    }



}}
