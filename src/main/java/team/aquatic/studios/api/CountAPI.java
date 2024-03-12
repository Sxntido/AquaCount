package team.aquatic.studios.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import team.aquatic.studios.AquaCount;
import team.aquatic.studios.commands.AquaCMD;

public class CountAPI extends PlaceholderExpansion {

    private AquaCount plugin;

    public CountAPI(AquaCount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "AquaCount";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        if (identifier.equals("time_format")) {
            return Format(player, identifier);
        } else if (identifier.equals("time_total")) {
            return NoFormat(player, identifier);
        }

        return null;
    }

    public String Format(Player player, String identifier) {
        if(identifier.equals("time_format")){
            if(AquaCMD.time == -1 || AquaCMD.time == -2){
                return "X";
            } else {
                int time = AquaCMD.time;
                if (time >= 60 * 60 * 24 * 30) {
                    int months = time / (60 * 60 * 24 * 30);
                    return months + "mo";
                } else if (time >= 60 * 60 * 24 * 7) {
                    int weeks = time / (60 * 60 * 24 * 7);
                    return weeks + "w";
                } else if (time >= 60 * 60 * 24) {
                    int days = time / (60 * 60 * 24);
                    return days + "d";
                } else if (time >= 60 * 60) {
                    int hours = time / (60 * 60);
                    return hours + "h";
                } else if (time >= 60) {
                    int minutes = time / 60;
                    return minutes + "m";
                } else {
                    return time + "s";
                }
            }
        }
        return null;
    }

    public String NoFormat(Player player, String identifier) {
        if (identifier.equals("time_total")) {
            if (AquaCMD.time == -1 || AquaCMD.time == -2) {
                return "X";
            } else {
                int time = AquaCMD.time;
                StringBuilder fullTime = new StringBuilder();
                int days = time / (60 * 60 * 24);
                time -= days * (60 * 60 * 24);
                int hours = time / (60 * 60);
                time -= hours * (60 * 60);
                int minutes = time / 60;
                time -= minutes * 60;
                int seconds = time;

                if (days > 0) {
                    fullTime.append(days).append("d ");
                }
                if (hours > 0 || fullTime.length() > 0) {
                    fullTime.append(hours).append("h ");
                }
                if (minutes > 0 || fullTime.length() > 0) {
                    fullTime.append(minutes).append("m ");
                }
                fullTime.append(seconds).append("s");

                return fullTime.toString().trim();
            }
        }
        return null;
    }
}
