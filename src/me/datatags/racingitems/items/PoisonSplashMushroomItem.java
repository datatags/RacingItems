package me.datatags.racingitems.items;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonSplashMushroomItem extends SplashMushroomItem {

	public PoisonSplashMushroomItem() {
		super("poison_splash_mushroom", 6, "Poison Splash Mushroom", 0.3f, 0.5f, 5, new PotionEffect(PotionEffectType.POISON, 100, 2), false);
	}

}
