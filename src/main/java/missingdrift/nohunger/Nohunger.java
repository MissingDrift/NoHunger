package missingdrift.nohunger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public final class Nohunger extends JavaPlugin implements Listener {

    private File configFile;
    private YamlConfiguration config;

    @Override
    public void onEnable() {
        System.out.println("\n" +
                "  _   _  ___  _   _ _   _ _   _  ____ _____ ____  \n" +
                " | \\ | |/ _ \\| | | | | | | \\ | |/ ___| ____|  _ \\ \n" +
                " |  \\| | | | | |_| | | | |  \\| | |  _|  _| | |_) |\n" +
                " | |\\  | |_| |  _  | |_| | |\\  | |_| | |___|  _ < \n" +
                " |_| \\_|\\___/|_| |_|\\___/|_| \\_|\\____|_____|_| \\_\\\n" +
                "                                                  \n" +
                "\n" +
                "Version: 1.0.0\n" +
                "Author: MissingDrift");
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        if(config.getBoolean("enable")){
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}