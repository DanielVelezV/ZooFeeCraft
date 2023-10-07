package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Interfaces.InventoryHandler;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;
import org.example.elwarriorcito.zoofee.Utils.ItemManager;
import org.example.elwarriorcito.zoofee.ZooFee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZooAnimalStatsGUI extends ZooInteractiveGUI {
    private List<ItemStack> Stats;
    private final ZooFeeAnimal Holder;
    private final GUIManager guiManager;

    public ZooAnimalStatsGUI(ZooFeeAnimal holder){
        super(holder);
        this.Holder = holder;
        this.guiManager = ZooFee.getGuiManager();
    }

    @Override
    public void onOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 1);
        this.decorate(p);
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0);
        this.guiManager.registerHandlerInventory(this.getInventory(), this);
    }

    @Override
    protected Inventory createMenu(ZooFeeAnimal holder) {
        return Bukkit.createInventory(null, 9 * 6, holder.Name);
    }

    public void OpenMenu(Player p){
        this.guiManager.openGUI(this, p);
    }

    @Override
    public void decorate(Player p) {
        int inventorySize = this.getInventory().getSize();

        for (int i = 0; i < inventorySize; i++) {

            if(i == 12){
                ZooGUIButton button = new ZooGUIButton().
                        creator(player -> new ItemStack(Material.PAINTING)).
                        consumer(event -> {
                            Player player = (Player) event.getWhoClicked();
                        });
                this.addButton(i, button);
            }
            this.getInventory().setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        super.decorate(p);
    }
}
