package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;
import org.bukkit.Location;
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
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;


public class ZooFeePig extends ZooFeeAnimal implements Listener {
    private Pig pig;
    public ZooFeePig(Location location, ZooAges age, ZooSex sex) {
        super(EntityType.PIG,
                location,
                ChatUtils.setColorName("&d&lZooPiggy"),
                sex,
                age);

        this.pig = (Pig) this.entity;
        //Menu

    }

    //region Events
    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e){
        if(e.getRightClicked() == this.pig && e.getHand().equals(EquipmentSlot.HAND)){
            this.Menu.ShowInventory(e.getPlayer());
        }
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


    //endregion
}
