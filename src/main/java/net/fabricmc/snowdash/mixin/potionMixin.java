package net.fabricmc.snowdash.mixin;

import net.fabricmc.snowdash.snowdashMain;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/item/PotionItem")
public class potionMixin extends Item {
	public potionMixin(Settings settings) {
		super(settings);
	}

	/**
	 *
	 * @author snowdash
	 * @param context
	 * @return
	 */
	//@Overwrite()
	public ActionResult useOnBlock(ItemUsageContext context){
		if(((Item)(Object)this) instanceof PotionItem){
			//ExampleMod.LOGGER.info("use potion");
			World world = context.getWorld();
			BlockPos bp = context.getBlockPos();
			BlockState bs = world.getBlockState(bp);
			PlayerEntity player = context.getPlayer();
			Hand hand = context.getHand();
			//ItemStack itemStack = context.getStack();
			if(bs.getBlock() == Blocks.DIRT){
				if(!world.isClient) {
					world.setBlockState(bp, snowdashMain.MUD.getDefaultState());
					if(!player.isCreative()) {
						player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
					}
				}else{
					world.playSound(player,bp, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS,1f,1f);
				}
			}
		}else{
			super.useOnBlock(context);
		}
		return ActionResult.SUCCESS;
	}
}