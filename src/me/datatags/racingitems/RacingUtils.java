package me.datatags.racingitems;

import java.util.List;

import org.bukkit.entity.Player;

import com.github.hornta.racing.RacingPlugin;
import com.github.hornta.racing.objects.RacePlayerSession;
import com.github.hornta.racing.objects.RaceSession;

public class RacingUtils {
    private RacingUtils() {}
    public static List<RacePlayerSession> getSessions(Player participant) {
        RaceSession session = getPlayerRace(participant);
        if (session == null) return null;
        List<RacePlayerSession> sessions = session.getPlayerSessions();
        sessions.sort(new RaceSessionPlaceComparator());
        return sessions;
    }
    public static RaceSession getPlayerRace(Player participant) {
        return RacingPlugin.getInstance().getRacingManager().getParticipatingRace(participant);
    }
}
