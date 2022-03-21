package me.datatags.racingitems.items;

import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.SoundPair;

public class BulletBillItem extends PotionEffectItem {
	public BulletBillItem() {
		super("bullet_bill", 3, "Bullet Bill", 1, 1, 4, new PotionEffect(PotionEffectType.SPEED, 60, 9));
	}
	
	@Override
	public SoundPair getPickupSound() {
	    return new SoundPair(Sound.BLOCK_AMETHYST_BLOCK_CHIME);
	}
	
	@Override
	public SoundPair getUseSound() {
	    return new SoundPair(Sound.ITEM_ELYTRA_FLYING, 2);
	}

	@Override
	public void onUse(PlayerInteractEvent e) {
	    super.onUse(e);
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	            e.getPlayer().stopSound(getUseSound().getSound());
	            new SoundPair(Sound.ITEM_ARMOR_EQUIP_NETHERITE).playTo(e.getPlayer());
	        }
	    }.runTaskLater(RacingItems.getInstance(), 60);
	}
}
