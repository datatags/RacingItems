package me.datatags.racingitems;

import org.bukkit.plugin.java.JavaPlugin;

import me.datatags.racingitems.items.BananaItem;

public class RacingItems extends JavaPlugin {
	private static RacingItems instance;
	private ItemManager im;
	@Override
	public void onEnable() {
		instance = this;
		im = new ItemManager();
		getCommand("giveracingitem").setExecutor(new GiveItemCommand(im));
		getCommand("positiondiagnostic").setExecutor(new PositionDiagnosticCommand(this));
		getServer().getPluginManager().registerEvents(new RacingListener(im), this);
	}
	@Override
	public void onDisable() {
		BananaItem.cleanup();
	}
	public static RacingItems getInstance() {
		return instance;
	}
	public ItemManager getItemManager() {
		return im;
	}
}
