package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;

public class ZooAxolotl extends ZooFeeAnimal {
    public ZooAxolotl(Location location, ZooAges Age, ZooSex Sex) {
        super(EntityType.AXOLOTL,
                location,
                ChatUtils.setColorName("&b&lZoo" + "&9&lAxolotl"),
                Sex,
                Age);


    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e) {
        if(e.getRightClicked() == this.entity && e.getHand().equals(EquipmentSlot.HAND)){
            this.Menu.ShowInventory(e.getPlayer());
        }
    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent e) {

    }
}
