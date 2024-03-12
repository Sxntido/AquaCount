package team.aquatic.studios.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.aquatic.studios.AquaCount;
import team.aquatic.studios.tools.Utils;

import java.util.List;

public class AquaCMD implements CommandExecutor {

    private AquaCount plugin;
    private BukkitRunnable currentTask = null;
    public static int time = -1;

    public AquaCMD(AquaCount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Use /aquacord help for a list of commands.");
            return true;
        }

        if (!sender.hasPermission("aquacount.admin")) {
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.permission"))));
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            try {
                int duration = Integer.parseInt(args[1]);
                String unit = args[2].toLowerCase();

                switch (unit) {
                    case "s":
                        time = duration;
                        break;
                    case "m":
                        time = duration * 60;
                        break;
                    case "h":
                        time = duration * 60 * 60;
                        break;
                    case "d":
                        time = duration * 60 * 60 * 24;
                        break;
                    case "w":
                        time = duration * 60 * 60 * 24 * 7;
                        break;
                    case "mo":
                        time = duration * 60 * 60 * 24 * 30;
                        break;
                    default:
                        sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.invalid"))));
                        return true;
                }

                if (currentTask != null) {
                    currentTask.cancel();
                }

                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                }

                currentTask = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (time <= 0) {
                            List<String> commands = plugin.getConfig().getStringList("commands");
                            for (String cmd : commands) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), cmd);
                            }
                            time = -1;
                            cancel();
                        }
                        time--;
                    }
                };
                currentTask.runTaskTimer(plugin, 20, 20);
                sender.sendMessage("Timer started for " + args[1] + " " + unit + ".");
            } catch (NumberFormatException e) {
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.number"))));
            }
            return true;
        } else if (args[0].equalsIgnoreCase("stop")) {
            if (currentTask != null) {
                currentTask.cancel();
                currentTask = null;
                time = -1;
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.stop"))));
            } else {
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.nothing"))));
            }
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("Hola");
            return true;
        }

        sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.unknown"))));
        return true;
    }
}