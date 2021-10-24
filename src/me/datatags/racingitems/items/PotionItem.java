package me.datatags.racingitems.items;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import me.datatags.racingitems.RacingItems;

public abstract class PotionItem extends RacingItem {
	protected PotionEffect effect;
	public PotionItem(String name, int model, String displayName, float minPos, float maxPos, int weight, PotionEffect effect) {
		super(name, model, displayName, minPos, maxPos, weight);
		this.effect = effect;
	}

	@Override
	public void applyTo(LivingEntity e) {
		ArmorStand as = e.getWorld().spawn(e.getLocation().add(0, 1, 0), ArmorStand.class, s -> {
			s.setSmall(true);
			s.setInvisible(true);
			s.getEquipment().setHelmet(item);
			s.setVelocity(e.getLocation().getDirection().multiply(2.5));
		});
		new BukkitRunnable() {
			@Override
			public void run() {
				if (as.isValid() && !as.isOnGround()) return;
				if (as.isOnGround()) {
					onLand(as.getLocation());
					as.remove();
				}
				this.cancel();
			}
		}.runTaskTimer(RacingItems.getInstance(), 1, 1);
	}
	public abstract void onLand(Location loc);
}
