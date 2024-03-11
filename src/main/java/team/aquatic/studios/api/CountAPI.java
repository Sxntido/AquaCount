package team.aquatic.studios.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import team.aquatic.studios.AquaCount;
import team.aquatic.studios.commands.Count;

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
        if(identifier.equals("contador_tiempo")){
            if(Count.tiempo == -1 || Count.tiempo == -2){
                return "X";
            } else {
                return String.valueOf(Count.tiempo);
            }
        }
        return null;
    }
}
