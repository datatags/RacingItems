package me.datatags.racingitems.items;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageLingeringMushroomItem extends LingeringMushroomItem {
	public DamageLingeringMushroomItem() {
		super("damage_lingering_mushroom", 8, "Damage Lingering Mushroom", 0, 1f, 3, new PotionEffect(PotionEffectType.HARM, 1, 2));
	}
}
