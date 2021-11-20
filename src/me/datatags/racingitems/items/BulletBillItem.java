package me.datatags.racingitems.items;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BulletBillItem extends PotionEffectItem {
	public BulletBillItem() {
		super("bullet_bill", 3, "Bullet Bill", 1, 1, 4, new PotionEffect(PotionEffectType.SPEED, 60, 9));
	}
}
