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
		int checkpointComparison = s2.getCurrentCheckpoint().compareTo(s1.getCurrentCheckpoint());
		if (checkpointComparison != 0) return checkpointComparison;
		RaceCheckpoint checkpoint = s1.getNextCheckpoint();
		// LOWER is better
		double distanceP1 = s1.getPlayer().getLocation().distanceSquared(checkpoint.getLocation());
		double distanceP2 = s2.getPlayer().getLocation().distanceSquared(checkpoint.getLocation());
		return Double.compare(distanceP1, distanceP2);
	}

}
