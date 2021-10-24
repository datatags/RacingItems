package me.datatags.racingitems.items;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessSplashMushroomItem extends SplashMushroomItem {

	public SlownessSplashMushroomItem() {
		super("slowness_splash_mushroom", 7, "Slowness Splash Mushroom", 0, 0.3f, 4, new PotionEffect(PotionEffectType.SLOW, 60, 2));
	}

}
