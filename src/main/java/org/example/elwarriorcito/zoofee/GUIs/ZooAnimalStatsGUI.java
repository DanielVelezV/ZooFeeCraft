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
            ItemStack MilkQuality = new ItemStack(Material.MILK_BUCKET, 1);
            ItemMeta MilkLore = MilkQuality.getItemMeta();
            MilkLore.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            MilkLore.addEnchant(Enchantment.ARROW_INFINITE, 3, true);
            MilkLore.setDisplayName(ChatUtils.setColorName("&r&f&lMilk Quality: &r&6&l" + animal.MilkQuality.label));
            List<String> MilkLoreDescription = new ArrayList<>(
                    Arrays.asList(
                            ChatUtils.setColorName("&r"),
                            ChatUtils.setColorName("&r&f&lNext Quality: &r&6&lVery good"))
            );

            MilkLore.setLore(MilkLoreDescription);
            MilkQuality.setItemMeta(MilkLore);

            this.Menu.setItem(13, MilkQuality);
        }
        // Age
        ItemStack Age = new ItemStack(Material.CLOCK, 1);
        ItemMeta Lore = Age.getItemMeta();
        Lore.setDisplayName(ChatUtils.setColorName("&r&f&lAge: &r&6&l" + this.Holder.Age.label));
        List<String> LoreDescription = new ArrayList<>(
                Arrays.asList(
                        ChatUtils.setColorName("&r"),
                        ChatUtils.setColorName("&r&f&lTime left: &r&l" + "45~ days"))
                        );

        Lore.setLore(LoreDescription);
        Age.setItemMeta(Lore);

        // Sex
        ItemStack Sex = this.Holder.Sex.equals(ZooSex.Male) ? new ItemStack(Material.STICK) : new ItemStack(Material.DRIED_KELP);
        String SexString = this.Holder.Sex.equals(ZooSex.Male) ? "&9&lMale ♂" : "&d&lFemale ♀";
        ItemMeta SexLore = Sex.getItemMeta();
        SexLore.setDisplayName(ChatUtils.setColorName("&r&f&lSex: " + SexString));

        Sex.setItemMeta(SexLore);

        // Ride Animal
        ItemStack Ride = new ItemStack(Material.SADDLE);
        ItemMeta RideLore = Ride.getItemMeta();
        RideLore.setDisplayName(ChatUtils.setColorName("&r&6&lRide"));

        List<String> RideLoreDescription = new ArrayList<>(
                Arrays.asList(
                        ChatUtils.setColorName("&r"),
                        ChatUtils.setColorName("&r&f&l&oWujuuuuuuuuuuu!"))
        );

        RideLore.setLore(RideLoreDescription);

        Ride.setItemMeta(RideLore);



        this.Menu.setItem(19, Age);
        this.Menu.setItem(22, Ride);
        this.Menu.setItem(25, Sex);


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
        if(e.getCurrentItem().getType() == Material.SADDLE){
            this.Holder.Ride((Player)e.getWhoClicked());
            this.CloseMenu((Player)e.getWhoClicked());
            e.setCancelled(true);
        }

        if(e.getClickedInventory().equals(this.Menu)){
            e.setCancelled(true);
        }


    }

}
