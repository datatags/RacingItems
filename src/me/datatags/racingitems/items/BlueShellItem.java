package me.datatags.racingitems.items;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;

public class BlueShellItem extends RacingItem implements Listener {
	private Set<LivingEntity> blueShelled = new HashSet<>();
	public BlueShellItem() {
		super("blue_shell", 2, "Blue Shell", 0.25f, 0.75f, 3);
		Bukkit.getPluginManager().registerEvents(this, RacingItems.getInstance());
	}

	@Override
	public void applyTo(LivingEntity e, Player player) {
		Player first = RacingUtils.getSessions(player).get(0).getPlayer();
		LivingEntity firstMount = first;
		if (firstMount.getVehicle() instanceof LivingEntity mount) {
			firstMount = mount;
		}
		blueShelled.add(firstMount);
		firstMount.teleport(firstMount.getLocation().add(0, 10, 0));
		firstMount.setVelocity(new Vector(0, 3, 0));
		firstMount.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3));
		first.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onFall(EntityDamageEvent e) {
		if (e.getCause() != DamageCause.FALL) return;
		if (!blueShelled.remove(e.getEntity())) return;
		e.setDamage(0);
		//((LivingEntity)e.getEntity()).removePotionEffect(PotionEffectType.SLOW);
	}
}
