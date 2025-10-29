package fr.tortostars.evolutivehoe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HoeGUI implements CommandExecutor {
    private final Main plugin;
    private final HoeManager hoeManager;

    public HoeGUI(Main plugin, HoeManager hoeManager) {
        this.plugin = plugin;
        this.hoeManager = hoeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        openProgressGUI(player);
        return true;
    }

    public void openProgressGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§6Progression de la Houe");
        int level = hoeManager.getHoeLevel(player);
        int exp = hoeManager.getExp(player);
        int seeds = hoeManager.getSeeds(player);

        // Exemple d'item pour afficher le niveau
        ItemStack levelItem = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = levelItem.getItemMeta();
        meta.setDisplayName("§bNiveau: " + level);
        levelItem.setItemMeta(meta);
        gui.setItem(13, levelItem);

        // Exemple d'item pour afficher l'EXP
        ItemStack expItem = new ItemStack(Material.EXPERIENCE_BOTTLE);
        meta = expItem.getItemMeta();
        meta.setDisplayName("§eEXP: " + exp + "/5000");
        expItem.setItemMeta(meta);
        gui.setItem(11, expItem);

        // Exemple d'item pour afficher les graines
        ItemStack seedItem = new ItemStack(Material.WHEAT_SEEDS);
        meta = seedItem.getItemMeta();
        meta.setDisplayName("§aGraines: " + seeds);
        seedItem.setItemMeta(meta);
        gui.setItem(15, seedItem);

        player.openInventory(gui);
    }
}
