package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.api.AquaCountAPI;
import team.aquatic.studios.commands.Executor;
import team.aquatic.studios.tools.Utils;

public class AquaCount extends JavaPlugin {

    PluginDescriptionFile aquatic = this.getDescription();

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF─█▀▀█ ░█▀▀█ ░█─░█ ─█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█─░█ ░█▄─░█ ▀▀█▀▀")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF░█▄▄█ ░█─░█ ░█─░█ ░█▄▄█ ░█─── ░█──░█ ░█─░█ ░█░█░█ ─░█──")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF░█─░█ ─▀▀█▄ ─▀▄▄▀ ░█─░█ ░█▄▄█ ░█▄▄▄█ ─▀▄▄▀ ░█──▀█ ─░█──")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#86BDFFInformation:")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fVersion: &#56B7FF"+aquatic.getVersion())));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fStatus: &#7DFF83Enabled")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fAuthor: &#7DFF83@Sxntido")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &fAll rights reserved by &#48D7FFAquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));

        this.getCommand("aquacount").setExecutor(new Executor(this));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new AquaCountAPI(this).register();
        }
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF─█▀▀█ ░█▀▀█ ░█─░█ ─█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█─░█ ░█▄─░█ ▀▀█▀▀")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF░█▄▄█ ░█─░█ ░█─░█ ░█▄▄█ ░█─── ░█──░█ ░█─░█ ░█░█░█ ─░█──")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&#56B7FF░█─░█ ─▀▀█▄ ─▀▄▄▀ ░█─░█ ░█▄▄█ ░█▄▄▄█ ─▀▄▄▀ ░█──▀█ ─░█──")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#86BDFFInformation:")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fVersion: &#56B7FF"+aquatic.getVersion())));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fStatus: &cDisabled")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &7● &fAuthor: &#7DFF83@Sxntido")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &fAll rights reserved by &#48D7FFAquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
    }
}