package team.aquatic.studios.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.aquatic.studios.AquaCount;

public class AquaCMD implements CommandExecutor {

    private AquaCount plugin;
    private BukkitRunnable currentTask = null; // Referencia a la tarea actual
    public static int tiempo = -1; // -1 significa no activo

    public AquaCMD(AquaCount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length > 1 && args[0].equalsIgnoreCase("start")) {
            try {
                int duracion = Integer.parseInt(args[1]);
                String unidad = args[2].toLowerCase();

                switch (unidad) {
                    case "s":
                        tiempo = duracion;
                        break;
                    case "m":
                        tiempo = duracion * 60;
                        break;
                    case "h":
                        tiempo = duracion * 60 * 60;
                        break;
                    case "d":
                        tiempo = duracion * 60 * 60 * 24;
                        break;
                    case "w":
                        tiempo = duracion * 60 * 60 * 24 * 7;
                        break;
                    case "mo":
                        tiempo = duracion * 60 * 60 * 24 * 30;
                        break;
                    default:
                        sender.sendMessage("Unidad de tiempo no reconocida. Usa: s (segundos), m (minutos), h (horas), d (días), w (semanas), mo (meses).");
                        return true;
                }

                if (currentTask != null) {
                    currentTask.cancel();
                }

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

                currentTask = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (tiempo <= 0) {
                            sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), plugin.getConfig().getString("comando-final"));
                            tiempo = -1;
                            cancel();
                        }
                        tiempo--;
                    }
                };
                currentTask.runTaskTimer(plugin, 20, 20);
                sender.sendMessage("Contador iniciado por " + args[1] + " " + unidad + ".");
            } catch (NumberFormatException e) {
                sender.sendMessage("Por favor, introduce un número válido para el tiempo.");
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("stop")) {
            if (currentTask != null) {
                currentTask.cancel();
                currentTask = null;
                tiempo = -1;
                sender.sendMessage("Contador detenido.");
            } else {
                sender.sendMessage("No hay un contador en ejecución.");
            }
            return true;
        }
        return false;
    }
}

