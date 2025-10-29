package fr.tortostars.evolutivehoe;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventListeners implements Listener {
    private final Main plugin;
    private final HoeManager hoeManager;

    public EventListeners(Main plugin, HoeManager hoeManager) {
        this.plugin = plugin;
        this.hoeManager = hoeManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();
        ItemStack tool = player.getInventory().getItemInMainHand();

        // Vérifie si le joueur utilise une houe évolutive
        if (tool.getItemMeta() != null && tool.getItemMeta().getDisplayName().contains("Houe Niveau")) {
            // Vérifie si le bloc est une culture
            if (isCrop(blockType)) {
                hoeManager.addExp(player, 1);
                // Logique de replantation automatique
                if (hoeManager.getSeeds(player) > 0) {
                    block.setType(Material.WHEAT); // Exemple pour le blé
                    hoeManager.setSeeds(player, hoeManager.getSeeds(player) - 1);
                }
                // Logique de loot bonus
                if (Math.random() < plugin.getConfig().getDouble("bonus-loot-chance")) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(blockType));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Logique pour gérer les clics dans le GUI
    }

    private boolean isCrop(Material material) {
        return material == Material.WHEAT ||
               material == Material.CARROTS ||
               material == Material.POTATOES ||
               material == Material.BEETROOTS;
    }
}
