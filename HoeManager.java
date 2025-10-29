package fr.tortostars.evolutivehoe;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class HoeManager {
    private final JavaPlugin plugin;
    private final HashMap<UUID, Integer> playerExp;
    private final HashMap<UUID, Integer> playerSeeds;

    public HoeManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerExp = new HashMap<>();
        this.playerSeeds = new HashMap<>();
    }

    public int getHoeLevel(Player player) {
        int exp = playerExp.getOrDefault(player.getUniqueId(), 0);
        if (exp >= 5000) return 3;
        if (exp >= 1500) return 2;
        return 1;
    }

    public void addExp(Player player, int amount) {
        playerExp.merge(player.getUniqueId(), amount, Integer::sum);
    }

    public int getExp(Player player) {
        return playerExp.getOrDefault(player.getUniqueId(), 0);
    }

    public void setSeeds(Player player, int amount) {
        playerSeeds.put(player.getUniqueId(), amount);
    }

    public int getSeeds(Player player) {
        return playerSeeds.getOrDefault(player.getUniqueId(), 0);
    }

    public void saveAllData() {
        // Sauvegarde des données dans un fichier ou une base de données
    }

    public ItemStack createHoe(int level) {
        ItemStack hoe = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = hoe.getItemMeta();
        meta.setDisplayName("§bHoue Niveau " + level);
        hoe.setItemMeta(meta);
        return hoe;
    }
}
