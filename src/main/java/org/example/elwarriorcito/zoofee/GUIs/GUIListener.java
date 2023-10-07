package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.example.elwarriorcito.zoofee.ZooFee;

public class GUIListener implements Listener {
    private final GUIManager Manager;

    public GUIListener(GUIManager manager){
        this.Manager = manager;
        ZooFee.getInstance().getServer().getPluginManager().registerEvents(this, ZooFee.getInstance());
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e){
        this.Manager.handleOpen(e);
    }@EventHandler
    public void onClose(InventoryCloseEvent e){
        this.Manager.handleClose(e);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        this.Manager.handleClick(e);
    }
}
