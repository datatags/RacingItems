package me.datatags.racingitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.event.player.PlayerInteractEvent;

import me.datatags.racingitems.items.BananaBunchItem;
import me.datatags.racingitems.items.BigBananaBunchItem;
import me.datatags.racingitems.items.BlooperItem;
import me.datatags.racingitems.items.BlueShellItem;
import me.datatags.racingitems.items.BulletBillItem;
import me.datatags.racingitems.items.DamageLingeringMushroomItem;
import me.datatags.racingitems.items.LightningItem;
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
		registerItem(new BlooperItem());
		registerItem(new BananaBunchItem());
		registerItem(new BigBananaBunchItem());
		registerItem(new BlueShellItem());
		registerItem(new LightningItem());
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
		for (RacingItem item : items.values()) {
			if (e.getItem().isSimilar(item.getItem())) {
				item.onUse(e);
				return;
			}
		}
		RacingItems.getInstance().getLogger().warning("Failed to find matching item :?");
	}
}
