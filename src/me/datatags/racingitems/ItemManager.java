package me.datatags.racingitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerInteractEvent;
import me.datatags.racingitems.items.BulletBillItem;
import me.datatags.racingitems.items.DamageLingeringMushroomItem;
import me.datatags.racingitems.items.PoisonSplashMushroomItem;
import me.datatags.racingitems.items.RacingItem;
import me.datatags.racingitems.items.SlownessLingeringMushroomItem;
import me.datatags.racingitems.items.SlownessSplashMushroomItem;
import me.datatags.racingitems.items.SpeedShakeItem;

public class ItemManager {
	private Map<String,RacingItem> items = new HashMap<>();
	private Random random = new Random();
	public ItemManager() {
		registerItem(new DamageLingeringMushroomItem());
		registerItem(new SlownessLingeringMushroomItem());
		registerItem(new PoisonSplashMushroomItem());
		registerItem(new SlownessSplashMushroomItem());
		registerItem(new BulletBillItem());
		registerItem(new SpeedShakeItem());
	}
	public void registerItem(RacingItem item) {
		items.put(item.getInternalName(), item);
	}
	public RacingItem getByName(String name) {
		return items.get(name);
	}
	public Map<String,RacingItem> getAllItems() {
		return items;
	}
	public RacingItem getRandomItem(float place) {
		List<RacingItem> selections = new ArrayList<>();
		for (RacingItem item : items.values()) {
			if ((item.getMinPos() <= place && item.getMaxPos() >= place) || place == 0) {
				for (int i = 0; i < item.getWeight(); i++) {
					selections.add(item);
				}
			}
		}
		return selections.get(random.nextInt(selections.size()));
	}
	public void handleUse(PlayerInteractEvent e) {
		LivingEntity target = e.getPlayer().getVehicle() instanceof LivingEntity ? (LivingEntity) e.getPlayer().getVehicle() : e.getPlayer();
		for (RacingItem item : items.values()) {
			if (e.getItem().isSimilar(item.getItem())) {
				item.applyTo(target);
				return;
			}
		}
		Bukkit.getLogger().info("Warning: failed to find matching item :?");
	}
}
