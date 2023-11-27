package me.datatags.racingitems.items;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessLingeringMushroomItem extends LingeringMushroomItem {
	public SlownessLingeringMushroomItem() {
		super("slowness_lingering_mushroom", 9, "Slowness Lingering Mushroom", 0, 0.6f, 3, new PotionEffect(PotionEffectType.SLOW, 50, 3));
	}
}
