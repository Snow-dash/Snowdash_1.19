package io.github.snowdash.snowdash;


import io.github.snowdash.snowdash.mixin.DispenserAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.Map;

public class DispenserAdd {
	public static void doPut() {
		Map<Item, DispenserBehavior> BEHAVIORS = DispenserAccessor.getBehaviors();
		BEHAVIORS.put(Items.POTION, new ItemDispenserBehavior() {

			@Override
			public ItemStack dispenseSilently(BlockPointer blockPointer, ItemStack itemStack) {
				Direction direction = blockPointer.getBlockState().get(DispenserBlock.FACING);
				BlockPos blockPos = blockPointer.getPos().offset(direction);
				ServerWorld world = blockPointer.getWorld();
				BlockState blockState = world.getBlockState(blockPos);
				//this.setSuccess(true);
				if (blockState.isOf(Blocks.DIRT)) {
					world.setBlockState(blockPos, SnowdashMain.MUD.getDefaultState());
					return new ItemStack(Items.GLASS_BOTTLE);
				} else {
					//itemStack.decrement(1);
					return super.dispenseSilently(blockPointer, itemStack);
				}
			}
		});
	}

}
