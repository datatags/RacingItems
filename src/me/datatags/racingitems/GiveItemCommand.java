package me.datatags.racingitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import me.datatags.racingitems.items.RacingItem;

public class GiveItemCommand implements TabExecutor {
	private ItemManager im;
	public GiveItemCommand(ItemManager im) {
		this.im = im;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be used as a player.");
			return true;
		}
		if (args.length != 1) return false;
		RacingItem item = im.getByName(args[0]);
		Map<Integer,ItemStack> items = ((Player)sender).getInventory().addItem(item.getItem());
		if (items.size() > 0) {
			sender.sendMessage(ChatColor.RED + "Your inventory is full.");
			return true;
		}
		sender.sendMessage(ChatColor.GREEN + "Success!");
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> options = new ArrayList<>();
		if (args.length != 1) return options;
		List<String> results = new ArrayList<>();
		options.addAll(im.getAllItems().keySet());
		StringUtil.copyPartialMatches(args[0], options, results);
		return results;
	}
}
