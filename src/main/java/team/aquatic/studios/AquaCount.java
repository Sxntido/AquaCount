package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.api.CountAPI;
import team.aquatic.studios.commands.AquaCMD;

public class AquaCount extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("aquacount").setExecutor(new AquaCMD(this));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CountAPI(this).register();
        }
        saveDefaultConfig();
    }
}