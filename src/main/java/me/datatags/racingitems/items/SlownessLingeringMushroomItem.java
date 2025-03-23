package me.datatags.racingitems.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessLingeringMushroomItem extends LingeringMushroomItem {
	public SlownessLingeringMushroomItem(ItemStack item) {
		super("slowness_lingering_mushroom", item, "Slowness Lingering Mushroom", 0, 0.6f, 3, new PotionEffect(PotionEffectType.SLOWNESS, 50, 3));
	}
}
