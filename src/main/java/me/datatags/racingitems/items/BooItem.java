package me.datatags.racingitems.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.hornta.racing.objects.RacePlayerSession;

import me.datatags.racingitems.RacingItems;
import me.datatags.racingitems.RacingUtils;
import me.datatags.racingitems.SoundPair;

public class BooItem extends RacingItem {

    public BooItem() {
        super("boo", 11, "Boo", 0.25f, 0.65f, 3);
    }

    @Override
    public void applyTo(LivingEntity vehicle, Player player) {
        PotionEffect invis = new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0);
        invis.apply(vehicle);
        invis.apply(player);
        boolean foundUser = false;
        ItemStack randomItem = null;
        List<RacePlayerSession> sessions = RacingUtils.getSessions(player);
        Collections.reverse(sessions);
        for (RacePlayerSession rps : sessions) {
            if (rps.getPlayer() == player) {
                foundUser = true;
                continue;
            }
            if (!foundUser) continue;
            Player target = rps.getPlayer();
            List<ItemStack> items = new ArrayList<>();
            ItemStack[] inv = target.getInventory().getContents();
            for (int i = 0; i < inv.length; i++) {
                if (inv[i] != null) {
                    items.add(inv[i]);
                }
            }
            if (items.size() == 0) continue;
            randomItem = items.get(ThreadLocalRandom.current().nextInt(items.size())).clone();
            randomItem.setAmount(1);
            target.getInventory().remove(randomItem);
            new SoundPair("mariokart:boo_steal").playTo(target);
            break;
        }
        if (randomItem == null) {
            randomItem = RacingItems.getInstance().getItemManager().getRandomItem(0).getItem();
        }
        player.getInventory().addItem(randomItem);
    }

    @Override
    public SoundPair getUseSound() {
        return new SoundPair("mariokart:boo_use");
    }
}
