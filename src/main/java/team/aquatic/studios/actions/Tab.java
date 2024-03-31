package team.aquatic.studios.actions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("start");
            completions.add("stop");
            completions.add("help");
            completions.add("reload");
        } else if (args.length == 3 && args[0].equalsIgnoreCase("start")) {
            completions.add("s");
            completions.add("m");
            completions.add("h");
            completions.add("d");
            completions.add("w");
            completions.add("mo");
            completions.add("seconds");
            completions.add("minutes");
            completions.add("hours");
            completions.add("days");
            completions.add("weeks");
            completions.add("months");
        }

        return completions;
    }
}
