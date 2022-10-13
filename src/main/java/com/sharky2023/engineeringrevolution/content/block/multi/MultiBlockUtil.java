package com.sharky2023.engineeringrevolution.content.block.multi;

import com.sharky2023.engineeringrevolution.content.block.ModBlocks;
import com.sharky2023.engineeringrevolution.content.block.tile.generators.SteamEngineBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MultiBlockUtil {

  public static boolean placeBlock(BlockState state, Level level, BlockPos pos, int update, boolean ignoreUnbreakable) {

        if (!MultiBlockUtil.isBlockUnbreakable(level, pos) /*&& (level.getBlockState(pos).getBlock().equals(Blocks.AIR.defaultBlockState())) */|| ignoreUnbreakable) {
            level.setBlock(pos, state, update);
            return true;
       }
        return false;
    }


    public static boolean placeBlock(BlockState state, Level level, BlockPos pos)
    {
        return MultiBlockUtil.placeBlock(state, level, pos, 3, false);
    }
    public static boolean placeBlock(BlockState state, Level level, BlockPos pos, boolean ignoreUnbreakable)
    {
        return MultiBlockUtil.placeBlock(state, level, pos, 3, ignoreUnbreakable);
    }




    public static boolean isBlockUnbreakable(Level level, BlockPos pos) {
        return level.getBlockState(pos).getDestroySpeed(level, pos) == -1 ;
    }


    public static void setupStructureSE(BlockPos pos, Level level, boolean areCoordsCorrect) {
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();

        if (!areCoordsCorrect) {
            BlockPos bottomLeft = findBottomCorner(ModBlocks.STEAM_ENGINE.get(),pos, level);
            cx = bottomLeft.getX();
            cy = bottomLeft.getY();
            cz = bottomLeft.getZ();
        }

        int i = 0;
        for (int x = cx; x < cx + 3; x++) {
            for (int z = cz; z < cz + 3; z++) {
                for (int y = cy; y < cy + 5; y++) {
                    i++;

                    //if (level.getBlockState(new BlockPos(x, y, z)).getBlock().equals(Blocks.AIR.defaultBlockState()));{


                    MultiBlockUtil.placeBlock(ModBlocks.STEAM_ENGINE.get().defaultBlockState(), level, new BlockPos(x, y, z), i == 45 ? 3 : 2, level.getBlockState(new BlockPos(x, y, z)).getBlock().equals(ModBlocks.STEAM_ENGINE));


                    BlockEntity tile = level.getBlockEntity(new BlockPos(x, y, z));
                    // Check if block is bottom center block
                    boolean master = (x == cx + 1 && y == cy + 1  && z == cz + 1);
                    if (tile instanceof SteamEngineBE) {
                        ((SteamEngineBE) tile).setMasterCoords(cx + 1, cy + 1, cz + 1);
                        ((SteamEngineBE) tile).setHasMaster(true);
                        ((SteamEngineBE) tile).setIsMaster(master);
                        }
                    }
                }
            }
        }
   // }


    public static BlockPos findBottomCorner(Block block, BlockPos pos, Level level)
    {
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();
        while(level.getBlockState(pos.offset(0, -1, 0)).getBlock().equals(block))
        {
            pos = pos.offset(0, -1, 0);
            cy--;
        }
        while(level.getBlockState(pos.offset(-1, 0, 0)).getBlock().equals(block))
        {
            pos = pos.offset(-1, 0, 0);
            cx--;
        }
        while(level.getBlockState(pos.offset(0, 0, -1)).getBlock().equals(block))
        {
            pos = pos.offset(0, 0, -1);
            cz--;
        }
        return new BlockPos(cx, cy, cz);
    }

    public static void removeStructureSE(BlockPos pos, Level level)
    {
        for(int x = pos.getX() - 1; x < pos.getX() + 2; x++)
            for(int y = pos.getY() - 1; y < pos.getY() + 4; y++)
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

    public boolean canBePlaced(BlockPos pos,Level level, int xx, int yy, int zz){

      int cx = pos.getX();
      int cy = pos.getY();
      int cz = pos.getZ();

        int i = 0;
         for (int x = cx; x < cx + xx; x++) {
             for (int z = cz; z < cz + zz; z++) {
                 for (int y = cy; y < cy + yy; y++) {
                     i++;

                     if (level.getBlockState(new BlockPos(x, y, z)).getBlock().equals(Blocks.AIR.defaultBlockState())) ;
                     {
                         return true;

                     }
                 }
             }
         }
        return false;
    }
}

