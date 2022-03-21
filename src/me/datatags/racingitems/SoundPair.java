package me.datatags.racingitems;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundPair {
    private final Sound sound;
    private final float pitch;
    
    public SoundPair(Sound sound) {
        this(sound, 1);
    }
    
    public SoundPair(Sound sound, float pitch) {
        this.sound = sound;
        this.pitch = pitch;
    }
    
    public void playTo(Player player) {
        player.playSound(player.getLocation(), sound, 1, pitch);
    }
    
    public Sound getSound() {
        return sound;
    }
    
    public float getPitch() {
        return pitch;
    }
}
