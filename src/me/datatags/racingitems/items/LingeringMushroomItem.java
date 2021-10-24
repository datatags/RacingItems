package me.datatags.racingitems.items;

import org.bukkit.Location;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.potion.PotionEffect;

public abstract class LingeringMushroomItem extends PotionItem {
	public LingeringMushroomItem(String name, int model, String displayName, float minPos, float maxPos, int weight,
			PotionEffect effect) {
		super(name, model, displayName, minPos, maxPos, weight, effect);
	}

	@Override
	public void onLand(Location loc) {
		loc.getWorld().spawn(loc.add(0, 1, 0), AreaEffectCloud.class, a -> {
			a.addCustomEffect(effect, true);
			a.setColor(effect.getType().getColor());
		});
	}

}
