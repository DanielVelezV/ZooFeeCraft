package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Interfaces.InventoryHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class ZooInteractiveGUI implements InventoryHandler {

    private final Inventory inventory;
    private final Map<Integer, ZooGUIButton> buttonMap = new HashMap<>();

    public ZooInteractiveGUI(ZooFeeAnimal holder) {
        this.inventory = this.createMenu(holder);
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void addButton(int slot, ZooGUIButton button){
        this.buttonMap.put(slot, button);
    }

    public void decorate(Player p){
        this.buttonMap.forEach((slot, button) -> {
            ItemStack btn = button.getIconCreator().apply(p);
            this.inventory.setItem(slot, btn);
        });
    }

    @Override
    public void onClick(InventoryClickEvent e){
        e.setCancelled(true);

        int slot = e.getSlot();

        ZooGUIButton button = this.buttonMap.get(slot);
        if(button != null){
            button.getEventConsumer().accept(e);
        }
    }
    @Override
    public void onOpen(InventoryOpenEvent e) {
        this.decorate((Player) e.getPlayer());
    }
    @Override
    public void onClose(InventoryCloseEvent e){}

    protected abstract Inventory createMenu(ZooFeeAnimal holder);
}
