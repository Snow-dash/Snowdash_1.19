package io.github.snowdash.snowdash.mixin;

import io.github.snowdash.snowdash.SnowdashMain;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PotionItem.class)
public abstract class PotionMixin extends Item {
	public PotionMixin(Settings settings) {
		super(settings);
	}

	/**
	 *
	 * @author snowdash
	 * @param context
	 * @return
	 */
	//@Overwrite()

	@Override
	public ActionResult useOnBlock(ItemUsageContext context){
		if(((Item)(Object)this) instanceof PotionItem){
			World world = context.getWorld();
			BlockPos bp = context.getBlockPos();
			BlockState bs = world.getBlockState(bp);
			PlayerEntity player = context.getPlayer();
			Hand hand = context.getHand();
			if(bs.getBlock() == Blocks.DIRT && player.getAbilities().allowModifyWorld){
				if(!world.isClient) {
					world.setBlockState(bp, SnowdashMain.MUD.getDefaultState());
					if(!player.isCreative()) {
						player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
					}
				}else{
					world.playSound(player,bp, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.PLAYERS,0.8f,0.9f);
					world.playSound(player,bp,SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS,0.8f,0.9f);
				}
				return ActionResult.SUCCESS;
			}
			else{
				return ActionResult.PASS;
			}
		}else{
			return super.useOnBlock(context);
		}
	}
}