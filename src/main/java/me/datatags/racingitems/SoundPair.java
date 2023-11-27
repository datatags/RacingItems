package me.datatags.racingitems;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundPair {
    private final Sound sound;
    private final String customSound;
    private final float pitch;

    public SoundPair(Sound sound) {
        this(sound, 1);
    }

    public SoundPair(String customSound) {
        this(customSound, 1);
    }

    public SoundPair(Sound sound, float pitch) {
        this.sound = sound;
        this.customSound = null;
        this.pitch = pitch;
    }

    public SoundPair(String customSound, float pitch) {
        this.sound = null;
        this.customSound = customSound;
        this.pitch = pitch;
    }

    public void playTo(Player player) {
        if (sound == null) {
            player.playSound(player.getLocation(), customSound, 1, pitch);
        } else {
            player.playSound(player.getLocation(), sound, 1, pitch);
        }
    }

    public void stopFor(Player player) {
        if (sound == null) {
            player.stopSound(customSound);
        } else {
            player.stopSound(sound);
        }
    }
}
