package me.datatags.racingitems.items;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public abstract class PotionEffectItem extends RacingItem {
	protected final PotionEffect effect;
	public PotionEffectItem(String name, ItemStack item, String displayName, float minPos, float maxPos, int weight, PotionEffect effect) {
		super(name, item, displayName, minPos, maxPos, weight);
		this.effect = effect;
	}

	@Override
	public void applyTo(LivingEntity e, Player player) {
		effect.apply(e);
	}
}
