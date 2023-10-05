package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;
import org.example.elwarriorcito.zoofee.Utils.ItemManager;
import org.example.elwarriorcito.zoofee.ZooFee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZooAnimalStatsGUI implements Listener {
    private Inventory Menu;
    private List<ItemStack> Stats;
    private ZooFeeAnimal Holder;
    public ZooAnimalStatsGUI(ZooFeeAnimal ZooAnimal){
        this.Holder = ZooAnimal;

        this.Menu = Bukkit.createInventory(null, 36, this.Holder.Name);

        ZooFee.getInstance().getServer().getPluginManager().registerEvents(this, ZooFee.getInstance());
    }

    public void CreateStats(){

        if(this.Holder instanceof ZooFeeAnimalMilkable && this.Holder.Sex.equals(ZooSex.Female)){
            ZooFeeAnimalMilkable animal = (ZooFeeAnimalMilkable) this.Holder;
            ItemStack MilkQuality = ItemManager.BuildItem("&r&f&lMilk Quality: &r&6&l" + animal.MilkQuality.label,
                    Material.MILK_BUCKET,
                    1,
                    new ArrayList<>(
                            Arrays.asList(
                                    ChatUtils.setColorName("&r"),
                                    ChatUtils.setColorName("&r&f&lNext Quality: &r&6&lVery good"))
                    ),
                    true);

            this.Menu.setItem(13, MilkQuality);
        }
        // Age
        ItemStack Age = ItemManager.BuildItem("&r&f&lAge: &r&6&l" + this.Holder.Age.label,
                Material.CLOCK,
                1,
                new ArrayList<>(
                        Arrays.asList(
                                ChatUtils.setColorName("&r"),
                                ChatUtils.setColorName("&r&f&lTime left: &r&l" + "45~ days"))
                ),
                true);


        // Sex
        Material Sex = this.Holder.Sex.equals(ZooSex.Male) ? Material.STICK : Material.DRIED_KELP;
        String SexString = this.Holder.Sex.equals(ZooSex.Male) ? "&9&lMale ♂" : "&d&lFemale ♀";
        ItemStack SexItem = ItemManager.BuildItem("&r&f&lSex: " + SexString, Sex, 1, null, null);


        // Ride Animal
        ItemStack Ride = ItemManager.BuildItem("&r&6&lRide",
                Material.SADDLE,
                1,
                new ArrayList<>(
                        Arrays.asList(
                                ChatUtils.setColorName("&r"),
                                ChatUtils.setColorName("&r&f&l&oWujuuuuuuuuuuu!"))
                ), true);




        this.Menu.setItem(19, Age);
        this.Menu.setItem(22, Ride);
        this.Menu.setItem(25, SexItem);


    }

    public void ShowInventory(Player player){
        this.CreateStats();
        player.openInventory(this.Menu);
    }

    public void CloseMenu(Player player){
        player.closeInventory();
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e){
        if(e.getClickedInventory().equals(this.Menu)){
            e.setCancelled(true);
        }
        if(e.getCurrentItem().getType() == Material.SADDLE){
            this.Holder.Ride((Player)e.getWhoClicked());
            this.CloseMenu((Player)e.getWhoClicked());
            e.setCancelled(true);
        }




    }

}
