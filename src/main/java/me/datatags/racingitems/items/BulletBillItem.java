package me.datatags.racingitems.items;

import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.SoundPair;

public class BulletBillItem extends PotionEffectItem {
	public BulletBillItem(ItemStack item) {
		super("bullet_bill", item, "Bullet Bill", 1, 1, 4, new PotionEffect(PotionEffectType.SPEED, 60, 9));
	}

	@Override
	public SoundPair getUseSound() {
	    return new SoundPair("bullet_bill");
	}

	@Override
	public void onUse(PlayerInteractEvent e) {
	    super.onUse(e);
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	            getUseSound().stopFor(e.getPlayer());
	            new SoundPair(Sound.ITEM_ARMOR_EQUIP_NETHERITE).playTo(e.getPlayer());
	        }
	    }.runTaskLater(RacingItems.getInstance(), 60);
	}
}
