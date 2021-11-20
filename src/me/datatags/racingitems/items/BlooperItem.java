package me.datatags.racingitems.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingUtils;

public class BlooperItem extends PotionEffectItem {

	public BlooperItem() {
		super("blooper", 1, "Blooper", 0.6f, 1f, 4, new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
	}

	@Override
	public void onUse(PlayerInteractEvent e) {
		for (RacePlayerSession playerSession : RacingUtils.getSessions(e.getPlayer())) {
			if (playerSession.getPlayer() == e.getPlayer()) break;
			applyTo(playerSession.getPlayer(), null);
		}
	}
}
