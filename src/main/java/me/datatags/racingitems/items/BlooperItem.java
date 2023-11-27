package me.datatags.racingitems.items;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;
import me.datatags.racingitems.SoundPair;

public class BlooperItem extends RacingItem {
    private final SoundPair hitSound = new SoundPair(Sound.ENTITY_SQUID_SQUIRT, 2);
    private final ItemStack pumpkin = new ItemStack(Material.PUMPKIN);

    public BlooperItem() {
        super("blooper", 1, "Blooper", 0.6f, 1f, 2);
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
        player.getEquipment().setHelmet(pumpkin);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.getEquipment().setHelmet(null);
            }
        }.runTaskLater(RacingItems.getInstance(), 100);
    }
}
