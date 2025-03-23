package me.datatags.racingitems.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageLingeringMushroomItem extends LingeringMushroomItem {
	public DamageLingeringMushroomItem(ItemStack item) {
		super("damage_lingering_mushroom", item, "Damage Lingering Mushroom", 0, 1f, 3, new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 2));
	}
}
