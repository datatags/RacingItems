package me.datatags.racingitems.items;

import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;

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
                new BabyHorseTimer(a).runTaskLater(RacingItems.getInstance(), effectTicks);
            }
            targetPlayer.getWorld().spigot().strikeLightning(targetPlayer.getLocation(), false);
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectTicks, 3));
            effectTicks += 20;
        }
    }
    private class BabyHorseTimer extends BukkitRunnable {
        private Ageable target;
        public BabyHorseTimer(Ageable target) {
            this.target = target;
            target.setBaby();
        }
        @Override
        public void run() {
            target.setAdult();
            target.setFireTicks(0);
        }
    }
}
