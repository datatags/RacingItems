package me.datatags.racingitems.items;

import org.bukkit.Sound;

import me.datatags.racingitems.SoundPair;

public class BigBananaBunchItem extends BananaItem {
	public BigBananaBunchItem() {
		super("big_banana_bunch", 10, "Big Banana Bunch", 0.6f, 1, 3, 3);
	}
	
	@Override
	public SoundPair getPickupSound() {
	    return new SoundPair(Sound.BLOCK_AMETHYST_BLOCK_CHIME);
	}
}
