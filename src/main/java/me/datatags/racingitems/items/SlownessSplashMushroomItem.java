package me.datatags.racingitems.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessSplashMushroomItem extends SplashMushroomItem {

	public SlownessSplashMushroomItem(ItemStack item) {
		super("slowness_splash_mushroom", item, "Slowness Splash Mushroom", 0, 0.3f, 4, new PotionEffect(PotionEffectType.SLOWNESS, 60, 2));
	}

}
