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
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Events.AnimalGrowEvent;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;

public class ZooBunny extends ZooFeeAnimal {
    public ZooBunny(ZooAges Age, ZooSex Sex) {
        super(EntityType.RABBIT,
                ChatUtils.setColorName("&6&lZoo" + "&7&lBunny"),
                Sex,
                Age);
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e) {
        super.onInteractEvent(e);
    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent e) {
        if(e.getEntity().equals(this.entity)){
            this.HolographicName.removeAll();
        }
    }

    @EventHandler
    public void onGrowEvent(AnimalGrowEvent e) {
        super.onGrowEvent(e);
    }
}
