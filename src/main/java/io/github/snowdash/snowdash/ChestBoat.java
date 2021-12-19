package io.github.snowdash.snowdash;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;

public class ChestBoat extends BoatEntity {

	public ChestBoat(EntityType<? extends BoatEntity> entityType, World world) {
		super(entityType, world);
	}

	public ChestBoat(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

}
