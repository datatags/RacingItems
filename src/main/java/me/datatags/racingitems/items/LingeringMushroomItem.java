package me.datatags.racingitems.items;

import org.bukkit.Location;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

import me.datatags.racingitems.RacingItems;

public abstract class LingeringMushroomItem extends ThrowableItem {
    private PotionEffect effect;

    public LingeringMushroomItem(String name, int model, String displayName, float minPos, float maxPos, int weight,
            PotionEffect effect) {
        super(name, model, displayName, minPos, maxPos, weight);
        this.effect = effect;
    }

    @Override
    public void onLand(Location loc) {
        loc.getWorld().spawn(loc.add(0, 1, 0), AreaEffectCloud.class, a -> {
            a.addCustomEffect(effect, true);
            a.setColor(effect.getType().getColor());
            a.setMetadata("RacingItems", new FixedMetadataValue(RacingItems.getInstance(), "yes"));
        });
    }

}
