package me.datatags.racingitems.items;

import java.util.Collections;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;
import me.datatags.racingitems.SoundPair;

public class LightningItem extends RacingItem {

    public LightningItem() {
        super("lightning", 4, "Lightning", 0.8f, 1, 3);
    }

    @Override
    public void applyTo(LivingEntity vehicle, Player player) {
        // starts at 3 seconds, increases 1 second for each player it hits
        int effectTicks = 60;
        List<RacePlayerSession> sessions = RacingUtils.getSessions(player);
        Collections.reverse(sessions); // loop back to front
        for (RacePlayerSession session : sessions) {
            Player targetPlayer = session.getPlayer();
            if (targetPlayer == player) continue;
            LivingEntity target = targetPlayer;
            if (targetPlayer.getVehicle() instanceof Ageable a) {
                target = a;
                new BabyHorseTimer(targetPlayer).runTaskLater(RacingItems.getInstance(), effectTicks);
            }
            targetPlayer.getWorld().spigot().strikeLightning(targetPlayer.getLocation(), false);
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectTicks, 3));
            effectTicks += 20;
        }
    }

    @Override
    public SoundPair getUseSound() {
        return new SoundPair(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2);
    }

    private class BabyHorseTimer extends BukkitRunnable {
        private Player player;
        public BabyHorseTimer(Player player) {
            this.player = player;
            ((Ageable)player.getVehicle()).setBaby();
        }
        @Override
        public void run() {
            Ageable vehicle = (Ageable) player.getVehicle();
            vehicle.setAdult();
            vehicle.setFireTicks(0);
        }
    }
}
