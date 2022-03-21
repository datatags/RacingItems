package me.datatags.racingitems.items;

import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;
import me.datatags.racingitems.SoundPair;

public class BlooperItem extends PotionEffectItem {
    private final SoundPair hitSound = new SoundPair(Sound.ENTITY_SQUID_SQUIRT, 2);
	public BlooperItem() {
		super("blooper", 1, "Blooper", 0.6f, 1f, 22, new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
	}

	@Override
	public void onUse(PlayerInteractEvent e) {
		for (RacePlayerSession playerSession : RacingUtils.getSessions(e.getPlayer())) {
			if (playerSession.getPlayer() == e.getPlayer()) break;
			applyTo(playerSession.getPlayer(), playerSession.getPlayer());
			hitSound.playTo(e.getPlayer());
		}
	}
	
	@Override
	public void applyTo(LivingEntity target, Player player) {
	    super.applyTo(target, player);
	    ArmorStand blooperStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 2, 0), EntityType.ARMOR_STAND);
	    player.addPassenger(blooperStand);
	    blooperStand.getEquipment().setHelmet(getItem());
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	            blooperStand.remove();
	        }
	    }.runTaskLater(RacingItems.getInstance(), 100);
	}
}
