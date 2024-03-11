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
        if(identifier.equals("contador_tiempo")){
            if(AquaCMD.tiempo == -1 || AquaCMD.tiempo == -2){
                return "X";
            } else {
                int tiempo = AquaCMD.tiempo;
                if (tiempo >= 60 * 60 * 24 * 30) { // Meses
                    int meses = tiempo / (60 * 60 * 24 * 30);
                    return meses + "mo";
                } else if (tiempo >= 60 * 60 * 24 * 7) {
                    int semanas = tiempo / (60 * 60 * 24 * 7);
                    return semanas + "w";
                } else if (tiempo >= 60 * 60 * 24) {
                    int dias = tiempo / (60 * 60 * 24);
                    return dias + "d";
                } else if (tiempo >= 60 * 60) {
                    int horas = tiempo / (60 * 60);
                    return horas + "h";
                } else if (tiempo >= 60) {
                    int minutos = tiempo / 60;
                    return minutos + "m";
                } else {
                    return tiempo + "s";
                }
            }
        }
        return null;
    }
}
