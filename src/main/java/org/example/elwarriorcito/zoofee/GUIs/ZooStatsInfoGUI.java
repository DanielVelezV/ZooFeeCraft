package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Interfaces.InventoryHandler;

public class ZooStatsInfoGUI extends ZooInteractiveGUI {

    public ZooStatsInfoGUI(ZooFeeAnimal holder) {
        super(holder);
    }

    @Override
    protected Inventory createMenu(ZooFeeAnimal holder) {
        return Bukkit.createInventory(null, 9 * 3, holder.Name);
    }
    
    

    @Override
    public void decorate(Player p) {
        int menuSize = this.getInventory().getSize();



        for (int i = 0; i < menuSize; i++) {
            Material M = i % 2 == 0 ? Material.WHITE_STAINED_GLASS_PANE : Material.BLACK_STAINED_GLASS_PANE;
            this.getInventory().setItem(i, new ItemStack(M));
        }
        
        
        super.decorate(p);
    }
}
