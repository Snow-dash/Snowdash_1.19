package io.github.snowdash.snowdash;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class RandomRedstoneBlock extends Block{
	public static final BooleanProperty OPENED = BooleanProperty.of("opened");
	public static final IntProperty POWER = IntProperty.of("power",0,15);
	private int nextPower = 0;

	public RandomRedstoneBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(OPENED, false));
		setDefaultState(getStateManager().getDefaultState().with(POWER,0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
		builder.add(OPENED, POWER);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
		world.setBlockState(pos, state.with(OPENED, !state.get(OPENED)));
		return ActionResult.SUCCESS;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
		if(state.get(OPENED)) {
			world.setBlockState(pos, state.with(POWER, random.nextInt(15)));
			nextPower = random.nextInt(15);
		}
	}

	@Override
	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	@Override
	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(POWER);
	}

	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return nextPower;
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify){
		SnowdashMain.LOGGER.info("neighbor");
	}

}
