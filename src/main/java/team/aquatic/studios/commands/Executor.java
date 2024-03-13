package team.aquatic.studios.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.aquatic.studios.AquaCount;
import team.aquatic.studios.api.AquaCountAPI;
import team.aquatic.studios.tools.Utils;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.List;

public class Executor implements CommandExecutor {

    private AquaCount plugin;
    private BukkitRunnable currentTask = null;
    public static int time = -1;

    public Executor(AquaCount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(Utils.CenterMessage("&r"))));
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color("     &#2E9CED⌚ &#2E9CED&lA&#30A5EF&lq&#32AFF2&lu&#33B8F4&la&#35C2F6&lC&#37CBF8&lo&#39D4FB&lu&#3ADEFD&ln&#3CE7FF&lt &f(v1.0 - @Sxntido)")));
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color("  &#B6D7FFA&#B6D7FFl&#B6D7FFl &#B6D7FFr&#B6D7FFi&#B6D7FFg&#B6D7FFh&#B6D7FFt&#B6D7FFs &#B6D7FFr&#B6D7FFe&#B6D7FFs&#B6D7FFe&#B6D7FFr&#B6D7FFv&#B6D7FFe&#B6D7FFd &#B6D7FFb&#B6D7FFy &#B6D7FFA&#B6D7FFq&#B6D7FFu&#B6D7FFa&#B6D7FFt&#B6D7FFi&#B6D7FFc &#B6D7FFS&#B6D7FFt&#B6D7FFu&#B6D7FFd&#B6D7FFi&#B6D7FFo&#B6D7FFs")));
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(Utils.CenterMessage("&r"))));
            return true;
        }

        if (!sender.hasPermission("aquacount.admin")) {
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.permission"))));
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            if (args.length < 3) {
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.usage"))));
                return true;
            }

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

                    String titleConfig = plugin.getConfig().getString("display.start_title");
                    String subtitleConfig = plugin.getConfig().getString("display.start_subtitle");

                    AquaCountAPI aquaCountAPI = new AquaCountAPI(plugin);

                    String timeFormat = aquaCountAPI.Format(null, "time_format");
                    String timeTotal = aquaCountAPI.NoFormat(null, "time_total");

                    String subtitle = subtitleConfig.replace("%unit%", unit).replace("%aquacount_time_total%", timeTotal).replace("%aquacount_time_format%", timeFormat).replace("%duration%", String.valueOf(duration));

                    titleConfig = Utils.translateHexColorCodes(Utils.Color(titleConfig));
                    subtitle = Utils.translateHexColorCodes(Utils.Color(subtitle));

                    player.sendTitle(titleConfig, subtitle, 10, 50, 20);

                }

                currentTask = new BukkitRunnable() {
                    @Override
                    public void run() {
                        time--;
                        if (time <= 0) {
                            List<String> commands = plugin.getConfig().getStringList("commands");
                            for (String cmd : commands) {
                                if (cmd.startsWith("[PLAYER]")) {
                                    String commandWithoutPrefix = cmd.replace("[PLAYER] ", "");
                                    if (sender instanceof Player) {
                                        Player player = (Player) sender;
                                        String parsedCommand = PlaceholderAPI.setPlaceholders(player, commandWithoutPrefix);
                                        player.performCommand(parsedCommand);
                                    } else {
                                        sender.sendMessage(Utils.Color("&cThis command can only be executed by one player"));
                                    }
                                } else if (cmd.startsWith("[CONSOLE]")) {
                                    String commandWithoutPrefix = cmd.replace("[CONSOLE] ", "");
                                    String parsedCommand = PlaceholderAPI.setPlaceholders(null, commandWithoutPrefix);
                                    sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), parsedCommand);
                                } else if (cmd.startsWith("[MESSAGE]")) {
                                    String messageWithoutPrefix = cmd.replace("[MESSAGE] ", "");
                                    if (sender instanceof Player) {
                                        Player player = (Player) sender;
                                        String parsedMessage = PlaceholderAPI.setPlaceholders(player, messageWithoutPrefix);
                                        player.sendMessage(Utils.translateHexColorCodes(Utils.Color(parsedMessage)));
                                    } else {
                                        sender.sendMessage(Utils.Color("&cThis action can only be performed by a player"));
                                    }
                                } else if (cmd.startsWith("[SOUND]")) {
                                    String soundWithoutPrefix = cmd.replace("[SOUND] ", "");
                                    if (sender instanceof Player) {
                                        Player player = (Player) sender;
                                        try {
                                            String[] parts = soundWithoutPrefix.split(",");
                                            Sound sound = Sound.valueOf(parts[0].trim().toUpperCase());
                                            float volume = parts.length > 1 ? Float.parseFloat(parts[1].trim()) : 1.0F;
                                            float pitch = parts.length > 2 ? Float.parseFloat(parts[2].trim()) : 1.0F;
                                            player.playSound(player.getLocation(), sound, volume, pitch);
                                        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                                            sender.sendMessage(Utils.Color("&cInvalid sound configuration: " + soundWithoutPrefix));
                                        }
                                    } else {
                                        sender.sendMessage(Utils.Color("&cThis action can only be performed by a player."));
                                    }
                                }
                            }
                            time = -1;
                            cancel();
                        }
                        time--;
                    }
                };
                currentTask.runTaskTimer(plugin, 20, 20);

                AquaCountAPI aquaCountAPI = new AquaCountAPI(plugin);

                String timeFormat = aquaCountAPI.Format(null, "time_format");
                String timeTotal = aquaCountAPI.NoFormat(null, "time_total");
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.started").replace("%unit%", unit).replace("%aquacount_time_total%", timeTotal).replace("%aquacount_time_format%", timeFormat).replace("%duration%", String.valueOf(duration)))));
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
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);

                    String title = Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("display.stop_title")));
                    String subtitle = Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("display.stop_subtitle")));

                    int fadeInTime = 10;
                    int stayTime = 50;
                    int fadeOutTime = 20;

                    player.sendTitle(title, subtitle, fadeInTime, stayTime, fadeOutTime);
                }

            } else {
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.nothing"))));
            }
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.reload"))));
            plugin.reloadConfig();
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Utils.CenterMessage("&r"));
            sender.sendMessage(Utils.CenterMessage("  &#2E9CED⌚ &#2E9CED&lA&#30A5EF&lq&#32AFF2&lu&#33B8F4&la&#35C2F6&lC&#37CBF8&lo&#39D4FB&lu&#3ADEFD&ln&#3CE7FF&lt &f(v1.0 - @Sxntido)"));
            sender.sendMessage(Utils.CenterMessage("&r"));
            sender.sendMessage(Utils.CenterMessage("  &#56B7FF/aquacount stop &7- &fStop the counter"));
            sender.sendMessage(Utils.CenterMessage("  &#56B7FF/aquacount reload &7- &fReload plugin files"));
            sender.sendMessage(Utils.CenterMessage("  &#56B7FF/aquacount help &7- &fSee the list of commands"));
            sender.sendMessage(Utils.CenterMessage("  &#56B7FF/aquacount start <time> <unit> &7- &fStart the counter"));
            sender.sendMessage(Utils.CenterMessage("&r"));
            sender.sendMessage(Utils.CenterMessage("  &#B6D7FFA&#B6D7FFl&#B6D7FFl &#B6D7FFr&#B6D7FFi&#B6D7FFg&#B6D7FFh&#B6D7FFt&#B6D7FFs &#B6D7FFr&#B6D7FFe&#B6D7FFs&#B6D7FFe&#B6D7FFr&#B6D7FFv&#B6D7FFe&#B6D7FFd &#B6D7FFb&#B6D7FFy &#B6D7FFA&#B6D7FFq&#B6D7FFu&#B6D7FFa&#B6D7FFt&#B6D7FFi&#B6D7FFc &#B6D7FFS&#B6D7FFt&#B6D7FFu&#B6D7FFd&#B6D7FFi&#B6D7FFo&#B6D7FFs"));
            sender.sendMessage(Utils.CenterMessage("&r"));
            return true;
        }

        sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.unknown"))));
        return true;
    }
}