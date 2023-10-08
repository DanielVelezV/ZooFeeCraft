package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Interfaces.InventoryHandler;
import oshi.util.platform.unix.openbsd.FstatUtil;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private final Map<Inventory, InventoryHandler> activeInventories = new HashMap<>();

    public void openGUI(ZooInteractiveGUI gui, Player p){
        this.registerHandlerInventory(gui.getInventory(), gui);
        p.openInventory(gui.getInventory());
    }
    public void registerHandlerInventory(Inventory inventory, InventoryHandler handler){
        this.activeInventories.put(inventory, handler);
    }
    public void unregisterInventory(Inventory inventory){
        this.activeInventories.remove(inventory);
    }
    public void handleClick(InventoryClickEvent e){
        InventoryHandler handler = activeInventories.get(e.getInventory());
        if(handler != null){
            handler.onClick(e);
        }
    }
    public void handleOpen(InventoryOpenEvent e){
        InventoryHandler handler = activeInventories.get(e.getInventory());
        if(handler != null){
            handler.onOpen(e);
        }
    }
    public void handleClose(InventoryCloseEvent e){
        InventoryHandler handler = activeInventories.get(e.getInventory());
        if(handler != null){
            handler.onClose(e);
            this.unregisterInventory(e.getInventory());
        }
    }
}
