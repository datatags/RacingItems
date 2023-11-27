package me.datatags.racingitems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.github.hornta.racing.objects.RaceCheckpoint;
import com.github.hornta.racing.objects.RacePlayerSession;

public class PositionDiagnosticCommand implements CommandExecutor {
    private RacingItems main;
    private Map<UUID,BukkitTask> tasks = new HashMap<>();
    public PositionDiagnosticCommand(RacingItems main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        BukkitTask task = tasks.get(player.getUniqueId());
        if (task != null) {
            task.cancel();
            sender.sendMessage(ChatColor.GREEN + "Disabled diagnostics.");
            return true;
        }
        int delay = 20;
        if (args.length > 0) {
            try {
                delay = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid tick delay.");
                return true;
            }
        }
        new DiagnosticTimer(player).runTaskTimer(main, delay, delay);
        sender.sendMessage(ChatColor.GREEN + "Diagnostics enabled");
        return true;
    }
    private class DiagnosticTimer extends BukkitRunnable {
        private Player target;
        public DiagnosticTimer(Player target) {
            this.target = target;
        }
        @Override
        public void run() {
            if (!target.isOnline()) {
                disable();
                return;
            }
            List<RacePlayerSession> sessions = RacingUtils.getSessions(target);
            if (sessions == null) {
                disable();
                target.sendMessage(ChatColor.RED + "Diagnostics disabled");
                return;
            }
            RacePlayerSession targetSession = null;
            for (RacePlayerSession session : sessions) {
                if (session.getPlayer() != target) continue;
                targetSession = session;
            }
            if (targetSession == null) {
                disable();
                target.sendMessage(ChatColor.RED + "Diagnostics disabled due to an error.");
                return;
            }
            target.sendMessage(ChatColor.GREEN + "Your place: " + (sessions.indexOf(targetSession) + 1) + "/" + targetSession.getRaceSession().getAmountOfParticipants());
            target.sendMessage(ChatColor.GREEN + "Your lap: " + targetSession.getCurrentLap());
            RaceCheckpoint cp = targetSession.getNextCheckpoint();
            target.sendMessage(ChatColor.GREEN + "Next checkpoint: " + cp.getPosition());
            target.sendMessage(ChatColor.GREEN + "Next checkpoint distance: " + cp.getLocation().distance(target.getLocation()));
        }
        private void disable() {
            this.cancel();
            tasks.remove(target.getUniqueId(), this);
        }
    }
}
