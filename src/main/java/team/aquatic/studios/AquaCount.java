package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.api.AquaCountAPI;
import team.aquatic.studios.actions.Executor;
import team.aquatic.studios.actions.Tab;
import team.aquatic.studios.register.Metrics;
import team.aquatic.studios.tools.Utils;

public class AquaCount extends JavaPlugin {

    PluginDescriptionFile aquatic = this.getDescription();

    @Override
    public void onEnable() {

        saveDefaultConfig();
        int pluginId = 21342;
        Metrics metrics = new Metrics(this, pluginId);
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED    ___                     ______                  __ ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED   /   | ____ ___  ______ _/ ____/___  __  ______  / /_")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED  / /| |/ __ `/ / / / __ `/ /   / __ \\/ / / / __ \\/ __/")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED / ___ / /_/ / /_/ / /_/ / /___/ /_/ / /_/ / / / / /_  ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED/_/  |_\\__, /\\__,_/\\__,_/\\____/\\____/\\__,_/_/ /_/\\__/  ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED         /_/                                           ")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#7DCDFFRunning &#1179FF" + aquatic.getVersion() + "-SNAPSHOT &#7DCDFF& Status: &a[Enabled]")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CEDAll rights reserved by &#1179FFSxntido & Aquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));

        this.getCommand("aquacount").setExecutor(new Executor(this));
        this.getCommand("aquacount").setTabCompleter(new Tab());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new AquaCountAPI(this).register();
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED    ___                     ______                  __ ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED   /   | ____ ___  ______ _/ ____/___  __  ______  / /_")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED  / /| |/ __ `/ / / / __ `/ /   / __ \\/ / / / __ \\/ __/")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED / ___ / /_/ / /_/ / /_/ / /___/ /_/ / /_/ / / / / /_  ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED/_/  |_\\__, /\\__,_/\\__,_/\\____/\\____/\\__,_/_/ /_/\\__/  ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CED         /_/                                           ")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#7DCDFFRunning &#1179FF" + aquatic.getVersion() + "-SNAPSHOT &#7DCDFF& Status: &c[Disabled]")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#2E9CEDAll rights reserved by &#1179FFSxntido & Aquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&r"));
    }
}