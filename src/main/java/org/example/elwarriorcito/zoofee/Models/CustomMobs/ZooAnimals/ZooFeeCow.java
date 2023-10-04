package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;

import org.bukkit.Location;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import org.bukkit.inventory.ItemStack;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;


public class ZooFeeCow extends ZooFeeAnimalMilkable {

    public ZooFeeCow(Location location, ZooAges age, ZooSex sex){
        super(EntityType.COW,
                location,
                ChatUtils.setColorName("&8&lZoo" + "&f&lCow"),
                sex,
                age);

    }



    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e) {
        if(e.getPlayer().getItemInHand().getType().equals(Material.BUCKET) && e.getHand().equals(EquipmentSlot.HAND)){
            this.onPlayerMilkEvent(e.getPlayer(), e.getRightClicked());
            return;
        }
        if(e.getRightClicked() == this.entity && e.getHand().equals(EquipmentSlot.HAND)){
            this.Menu.ShowInventory(e.getPlayer());
            System.out.println(e.getRightClicked().getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent e)
    {
        if (e.getItemStack().getType() == Material.MILK_BUCKET)
        {
            e.setItemStack(new ItemStack(Material.BUCKET));
            e.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent e) {
        if(e.getEntity().equals(this.entity)){
            this.HolographicName.removeAll();
            e.getDrops().clear();
        }
    }


}
