package me.datatags.racingitems.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import me.datatags.racingitems.RacingItems;
import org.bukkit.inventory.ItemStack;

public class BananaItem extends ThrowableItem {
	private static final Set<BlockReverter> tasks = new HashSet<>();
	private final int size;
	public BananaItem(String name, ItemStack item, String displayName, float minPos, float maxPos, int weight, int size) {
		super(name, item, displayName, minPos, maxPos, weight);
		this.size = size;
	}

	@Override
	public void onLand(Location loc) {
		int radius = size - 1;
		int sizePositive = (int)Math.ceil(radius / 2d);
		int sizeNegative = (int)Math.floor(radius / 2d) * -1;
		Set<BlockState> beneathStates = new HashSet<>();
		Set<BlockState> berryStates = new HashSet<>();
		for (int i = sizeNegative; i <= sizePositive; i++) {
			for (int j = sizeNegative; j <= sizePositive; j++) {
				Block block = loc.clone().add(i, 0, j).getBlock();
				Block beneath = block.getRelative(BlockFace.DOWN);
				if (isBlockReverting(block.getLocation()) || isBlockReverting(beneath.getLocation())) continue;
				berryStates.add(block.getState());
				beneathStates.add(beneath.getState());
				beneath.setType(Material.DIRT);
				block.setType(Material.SWEET_BERRY_BUSH);
				Ageable data = (Ageable) block.getBlockData();
				data.setAge(data.getMaximumAge());
				block.setBlockData(data);
			}
		}
		List<BlockState> states = new ArrayList<>();
		// remove berry states first so we don't leave berries on the ground
		states.addAll(berryStates);
		states.addAll(beneathStates);
		BlockReverter reverter = new BlockReverter(states);
		tasks.add(reverter);
		reverter.runTaskLater(RacingItems.getInstance(), 60 * 20);
	}
	private boolean isBlockReverting(Location loc) {
		for (BlockReverter task : tasks) {
			for (BlockState state : task.getStates()) {
				if (state.getLocation().equals(loc)) return true;
			}
		}
		return false;
	}
	public static void cleanup() {
		for (BlockReverter task : tasks) {
			task.cancel();
			task.run();
		}
		tasks.clear();
	}
	public static void taskFinished(BlockReverter reverter) {
		tasks.remove(reverter);
	}
}
