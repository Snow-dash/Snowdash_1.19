package io.github.snowdash.snowdash.mixin;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(DispenserBlock.class)
public interface DispenserAccessor {

	@Accessor("BEHAVIORS")
	public static Map<Item, DispenserBehavior> getBehaviors(){
		throw new AssertionError();
	}

}



