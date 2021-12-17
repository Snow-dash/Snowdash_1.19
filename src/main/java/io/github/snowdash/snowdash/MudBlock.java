package io.github.snowdash.snowdash;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class MudBlock extends Block {
	public MudBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		//super.randomTick(state, world, pos, random);
		BlockPos pos1 =pos.down();
		BlockPos pos2 =pos1.down();
		boolean bl1 = world.getBlockState(pos1).getBlock() == Blocks.DRIPSTONE_BLOCK;
		boolean bl2 = world.getBlockState(pos2).getBlock() == Blocks.POINTED_DRIPSTONE;
		if(bl1 && bl2){
			BlockState bs = world.getBlockState(pos2);
			Direction a = bs.get(Properties.VERTICAL_DIRECTION);
			if(a.getName().equals("down") && random.nextInt(10) == 0){
				world.setBlockState(pos,Blocks.CLAY.getDefaultState());
			}
		}
	}
}