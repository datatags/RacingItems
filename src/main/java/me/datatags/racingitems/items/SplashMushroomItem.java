package me.datatags.racingitems.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

public class SplashMushroomItem extends ThrowableItem {
	private PotionEffect effect;
	private boolean applyToMount;
	public SplashMushroomItem(String name, int model, String displayName, float minPos, float maxPos, int weight,
	PotionEffect effect) {
		this(name, model, displayName, minPos, maxPos, weight, effect, true);
	}

	public SplashMushroomItem(String name, int model, String displayName, float minPos, float maxPos, int weight,
			PotionEffect effect, boolean applyToMount) {
		super(name, model, displayName, minPos, maxPos, weight);
		this.effect = effect;
		this.applyToMount = applyToMount;
	}

	@Override
	public void onLand(Location loc) {
		for (Entity entity : loc.getWorld().getNearbyEntities(loc, 3, 5, 3)) {
			if (entity.getType() != EntityType.PLAYER) continue;
			Entity target = entity;
			if (applyToMount && target.getVehicle() instanceof LivingEntity) {
				target = entity.getVehicle();
			}
			effect.apply((LivingEntity)target);
		}
		for (int i = 0; i < 360; i += 10) {
			double x = Math.sin(Math.toRadians(i)) * 3;
			double z = Math.cos(Math.toRadians(i)) * 3;
			Location newLoc = loc.clone().add(x, 1, z);
			newLoc.getWorld().spawnParticle(Particle.GLOW, newLoc, 3);
		}
	}

}
