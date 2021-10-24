package me.datatags.racingitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import me.datatags.racingitems.RacingItems;

public abstract class RacingItem {
	/*BANANA(1, "Banana", 0, 1, 1),
	BLOOPER(2, "Blooper", 0, 1, 1),
	BLUE_SHELL(3, "Blue Shell", 0, 1, 1),
	BULLET_BILL(4, "Bullet Bill", 1, 1, 3),
	LIGHTNING(5, "Lightning", 1, 1, 1),
	SPEED_POTION(-1, "Turbo", 0, 1, 3, new PotionEffect(PotionEffectType.SPEED, 60, 2)),
	SLOWNESS_SPLASH(-1, "Volt Shroom", 0, 1, 5, new PotionEffect(PotionEffectType.SLOW, 20, 2)),
	SLOWNESS_LINGERING(-1, "Electro-shroom", 0, 1, 5, new PotionEffect(PotionEffectType.SLOW, 20, 2)),
	HARM_LINGERING(-1, "POW Block", 0, 1, 5, new PotionEffect(PotionEffectType.HARM, 20, 1)),
	POISON_SPLASH(-1, "Bomb", 0, 1, 5, new PotionEffect(PotionEffectType.POISON, 60, 2)),*/
	public static final NamespacedKey ITEM_KEY = new NamespacedKey(RacingItems.getInstance(), "item");
	private String name;
	private String displayName;
	private float minPos;
	private float maxPos;
	private int weight;
	protected ItemStack item;
	public RacingItem(String name, int model, String displayName, float minPos, float maxPos, int weight) {
		this.name = name;
		this.displayName = displayName;
		this.minPos = minPos;
		this.maxPos = maxPos;
		this.weight = weight;
		item = new ItemStack(Material.RAW_COPPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + displayName);
		meta.setCustomModelData(5050000 + model * 100);
		meta.getPersistentDataContainer().set(ITEM_KEY, PersistentDataType.STRING, name);
		item.setItemMeta(meta);
	}
	public String getInternalName() {
		return name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public float getMinPos() {
		return minPos;
	}
	public float getMaxPos() {
		return maxPos;
	}
	public int getWeight() {
		return weight;
	}
	public ItemStack getItem() {
		return item.clone();
	}
	public abstract void applyTo(LivingEntity e);
}
