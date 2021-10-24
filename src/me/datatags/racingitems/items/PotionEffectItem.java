package me.datatags.racingitems.items;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

public abstract class PotionEffectItem extends RacingItem {
	protected PotionEffect effect;
	public PotionEffectItem(String name, int model, String displayName, float minPos, float maxPos, int weight, PotionEffect effect) {
		super(name, model, displayName, minPos, maxPos, weight);
		this.effect = effect;
	}

	@Override
	public void applyTo(LivingEntity e) {
		effect.apply(e);
	}
}
