package me.datatags.racingitems;

import org.bukkit.plugin.java.JavaPlugin;

public class RacingItems extends JavaPlugin {
	private static RacingItems instance;
	private ItemManager im;
	@Override
	public void onEnable() {
		instance = this;
		im = new ItemManager();
		getCommand("giveracingitem").setExecutor(new GiveItemCommand(im));
		getServer().getPluginManager().registerEvents(new RacingListener(im), this);
	}
	public static RacingItems getInstance() {
		return instance;
	}
	public ItemManager getItemManager() {
		return im;
	}
}
