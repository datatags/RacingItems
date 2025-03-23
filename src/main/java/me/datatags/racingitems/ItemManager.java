package me.datatags.racingitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import me.datatags.racingitems.items.BananaBunchItem;
import me.datatags.racingitems.items.BigBananaBunchItem;
import me.datatags.racingitems.items.BlooperItem;
import me.datatags.racingitems.items.BlueShellItem;
import me.datatags.racingitems.items.BooItem;
import me.datatags.racingitems.items.BulletBillItem;
import me.datatags.racingitems.items.DamageLingeringMushroomItem;
import me.datatags.racingitems.items.LightningItem;
import me.datatags.racingitems.items.PoisonSplashMushroomItem;
import me.datatags.racingitems.items.RacingItem;
import me.datatags.racingitems.items.SlownessLingeringMushroomItem;
import me.datatags.racingitems.items.SlownessSplashMushroomItem;
import me.datatags.racingitems.items.SpeedShakeItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	private final Map<String,RacingItem> items = new HashMap<>();
	private final Random random = new Random();
	public ItemManager() {
		registerItem(new DamageLingeringMushroomItem(custom(Material.SKULL_BANNER_PATTERN)));
		registerItem(new SlownessLingeringMushroomItem(custom(Material.GUSTER_BANNER_PATTERN)));
		registerItem(new PoisonSplashMushroomItem(custom(Material.HEARTBREAK_POTTERY_SHERD)));
		registerItem(new SlownessSplashMushroomItem(custom(Material.FLOW_POTTERY_SHERD)));
		registerItem(new BulletBillItem(custom(Material.MINECART)));
		registerItem(new SpeedShakeItem(custom(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE)));
		registerItem(new BlooperItem(custom(Material.INK_SAC)));
		registerItem(new BananaBunchItem(custom(Material.TALL_GRASS)));
		registerItem(new BigBananaBunchItem(custom(Material.FERN)));
		registerItem(new BlueShellItem(custom(Material.SHULKER_SHELL)));
		registerItem(new LightningItem(custom(Material.BLAZE_ROD)));
		registerItem(new BooItem(custom(Material.OMINOUS_BOTTLE)));
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

	private static ItemStack custom(Material mat) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(64);
		item.setItemMeta(meta);
		return item;
	}
}
