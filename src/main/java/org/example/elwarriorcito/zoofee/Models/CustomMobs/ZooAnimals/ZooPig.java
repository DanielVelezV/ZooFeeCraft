package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Events.AnimalGrowEvent;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;


public class ZooPig extends ZooFeeAnimal implements Listener {
    private Pig pig;
    public ZooPig(ZooAges age, ZooSex sex) {
        super(EntityType.PIG,
                ChatUtils.setColorName("&d&lZooPiggy"),
                sex,
                age);

        this.pig = (Pig) this.entity;
        //Menu

    }

    //region Events
    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e){
        super.onInteractEvent(e);
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e){
        if(e.getClickedInventory().equals(this.Menu)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent e){
        if(e.getEntity().equals(this.entity)){
            this.HolographicName.removeAll();
        }
    }

    @EventHandler
    public void onGrowEvent(AnimalGrowEvent e) {
        super.onGrowEvent(e);
    }


    //endregion
}
