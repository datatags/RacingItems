package me.datatags.racingitems.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedShakeItem extends PotionEffectItem {

	public SpeedShakeItem(ItemStack item) {
		super("speed_shake", item, "Speed Shake", 0.5f, 1, 3, new PotionEffect(PotionEffectType.SPEED, 20, 4));
	}

}
