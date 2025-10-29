package fr.tortostars.evolutivehoe;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private HoeManager hoeManager;

    @Override
    public void onEnable() {
        this.hoeManager = new HoeManager(this);
        getServer().getPluginManager().registerEvents(new EventListeners(this, hoeManager), this);
        getCommand("hoeprogress").setExecutor(new HoeGUI(this, hoeManager));
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        hoeManager.saveAllData();
    }
}
