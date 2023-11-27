package me.datatags.racingitems;

import java.util.Comparator;

import com.github.hornta.racing.objects.RaceCheckpoint;
import com.github.hornta.racing.objects.RacePlayerSession;

public class RaceSessionPlaceComparator implements Comparator<RacePlayerSession> {

	@Override
	public int compare(RacePlayerSession s1, RacePlayerSession s2) {
		if (s1.isFinished() && s2.isFinished()) return 0;
		// HIGHER is better
		int finishedComparison = Boolean.compare(s2.isFinished(), s1.isFinished());
		if (finishedComparison != 0) return finishedComparison;
		// HIGHER is better
		int lapComparison = Integer.compare(s2.getCurrentLap(), s1.getCurrentLap());
		if (lapComparison != 0) return lapComparison;
		// HIGHER is better
		// Don't use "currentCheckpoint", it does something different.
		RaceCheckpoint cp1 = s1.getNextCheckpoint();
		RaceCheckpoint cp2 = s2.getNextCheckpoint();
		int checkpointComparison = cp2.compareTo(cp1);
		if (checkpointComparison != 0) return checkpointComparison;
		// LOWER is better
		// they're the same checkpoint at this point but just for non-confusion
		// I used cp1 and cp2
		double distanceP1 = s1.getPlayer().getLocation().distanceSquared(cp1.getLocation());
		double distanceP2 = s2.getPlayer().getLocation().distanceSquared(cp2.getLocation());
		return Double.compare(distanceP1, distanceP2);
	}

}
