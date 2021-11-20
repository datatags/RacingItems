package me.datatags.racingitems.items;

import java.util.List;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockReverter extends BukkitRunnable {
	private List<BlockState> states;
	public BlockReverter(List<BlockState> states) {
		this.states = states;
	}
	@Override
	public void run() {
		for (BlockState state : states) {
			state.update(true);
		}
		BananaItem.taskFinished(this);
	}
	public List<BlockState> getStates() {
		return states;
	}
}
