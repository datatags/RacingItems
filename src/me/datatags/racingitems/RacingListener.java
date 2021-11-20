package me.datatags.racingitems;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import com.github.hornta.racing.events.CheckpointReachedEvent;
import com.github.hornta.racing.objects.RacePlayerSession;
import me.datatags.racingitems.items.RacingItem;

public class RacingListener implements Listener {
	private ItemManager im;
	public RacingListener(ItemManager im) {
		this.im = im;
	}
	@EventHandler
	public void onCheckpoint(CheckpointReachedEvent e) {
		if (e.getPlayerSession().isFinished()) return;
		if (e.getCheckpoint().getPosition() % 2 == 1) return;
		Player player = e.getPlayerSession().getPlayer();
		float place = (calculatePlace(e.getPlayerSession()) + 1) / (float)e.getRaceSession().getAmountOfParticipants();
		RacingItem item = im.getRandomItem(place);
		Map<Integer,ItemStack> returnedItems = player.getInventory().addItem(item.getItem());
		if (returnedItems.size() == 0) {
			player.sendMessage(ChatColor.GREEN + "You've received an item!");
			player.sendMessage(ChatColor.GREEN + "Your place: " + place);
		}
	}
	@EventHandler
	public void onUseItem(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.SWEET_BERRY_BUSH) {
			e.setCancelled(true);
		}
		ItemStack item = e.getItem();
		if (item == null) return;
		if (item.getType() != Material.RAW_COPPER) return;
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (!item.getItemMeta().getPersistentDataContainer().has(RacingItem.ITEM_KEY, PersistentDataType.STRING)) return;
		e.setCancelled(true);
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			// clone so it doesn't modify the event's actual copy of the item
			item = item.clone();
			item.setAmount(item.getAmount() - 1);
			if (e.getHand() == EquipmentSlot.HAND) {
				e.getPlayer().getInventory().setItemInMainHand(item);
			} else {
				e.getPlayer().getInventory().setItemInOffHand(item);
			}
		}
		im.handleUse(e);
	}
	private int calculatePlace(RacePlayerSession session) {
		List<RacePlayerSession> playerSessions = session.getRaceSession().getPlayerSessions();
		if (playerSessions.size() == 1) return -1; // -1 + 1 == 0
		playerSessions.sort(new RaceSessionPlaceComparator());
		return playerSessions.indexOf(session);
	}
}
