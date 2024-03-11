package team.aquatic.studios.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import team.aquatic.studios.AquaCount;

public class Count implements CommandExecutor {

    private AquaCount plugin;
    public static int tiempo = -1; // -1 significa no activo

    public Count(AquaCount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("start")) {
            int duracion = plugin.getConfig().getInt("tiempo-segundos");
            tiempo = duracion;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (tiempo == 0) {
                        sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), plugin.getConfig().getString("comando-final"));
                        tiempo = -1;
                        cancel();
                    }
                    tiempo--;
                }
            }.runTaskTimer(plugin, 20, 20);
            sender.sendMessage("Contador iniciado.");
            return true;
        }
        return false;
    }
}
