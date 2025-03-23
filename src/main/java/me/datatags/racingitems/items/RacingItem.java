package me.datatags.racingitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.SoundPair;

public abstract class RacingItem {
	public static final NamespacedKey ITEM_KEY = new NamespacedKey(RacingItems.getInstance(), "item");
	private final String name;
	private final String displayName;
	private final float minPos;
	private final float maxPos;
	private final int weight;
	protected final ItemStack item;
	public RacingItem(String name, ItemStack item, String displayName, float minPos, float maxPos, int weight) {
		this.name = name;
		this.displayName = displayName;
		this.minPos = minPos;
		this.maxPos = maxPos;
		this.weight = weight;
		this.item = item;
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + displayName);
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

	public SoundPair getPickupSound() {
	    return new SoundPair("mariokart:checkpoint");
	}

	public SoundPair getUseSound() {
	    return new SoundPair("mariokart:throw");
	}

	public void onUse(PlayerInteractEvent e) {
	    getUseSound().playTo(e.getPlayer());
		LivingEntity target = e.getPlayer().getVehicle() instanceof LivingEntity ? (LivingEntity) e.getPlayer().getVehicle() : e.getPlayer();
		applyTo(target, e.getPlayer());
	}

	public abstract void applyTo(LivingEntity vehicle, Player player);
}
