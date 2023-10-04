package org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooMilkQuality;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;
import org.example.elwarriorcito.zoofee.ZooFee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ZooFeeAnimalMilkable extends ZooFeeAnimal{

    protected Boolean isMilkable = true;
    protected double MilkingCooldown = 15;
    protected double CurrentMilkingCoolDown = 0;
    protected Boolean hasBreeded = false;
    public ZooMilkQuality MilkQuality;
    private int taskID;

    public ZooFeeAnimalMilkable(EntityType type, Location location, String Name, ZooSex Sex, ZooAges Age) {
        super(type, location, Name, Sex, Age);

        this.HolographicName.AddLine(ChatUtils.setColorName("&a&l!"));
        this.MilkQuality = ZooMilkQuality.Very_Good;
    }

    public void onPlayerMilkEvent(Player player, Entity MilkedEntity){
        if(this.entity.equals(MilkedEntity)){
            if(this.Sex.equals(ZooSex.Male)){
                player.sendMessage(ChatUtils.setColorName("&lYou can't milk a &r&9&lMale"));
                return;
            }
            if(this.Age.equals(ZooAges.Baby)){
                player.sendMessage(ChatUtils.setColorName("&lYou can't milk a &r&d&lBaby"));
                return;
            }
            if(this.CurrentMilkingCoolDown > 0){
                player.sendMessage("The Cow: " + this.Name + " has been milked already. Cooldown now is: " + this.CurrentMilkingCoolDown);

            }else if(this.CurrentMilkingCoolDown == 0){
                this.HolographicName.EditLine(2, ChatUtils.setColorName("&r "));
                player.sendMessage("You just milked " + this.Name + ". Cooldown now is: " + this.MilkingCooldown);
                this.CurrentMilkingCoolDown = this.MilkingCooldown;
                ItemStack item = this.giveMilkBasedOnQuality();
                player.getInventory().addItem(item);
                this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(ZooFee.getInstance(),
                        this::LowerCooldown,
                        0,
                        20);
            }

        }
    }
    private void LowerCooldown(){
        if(this.CurrentMilkingCoolDown > 0){
            this.CurrentMilkingCoolDown -= 1;
        }

        if(this.CurrentMilkingCoolDown <= 0){
            Bukkit.getScheduler().cancelTask(taskID);
            this.HolographicName.EditLine(2, ChatUtils.setColorName("&a&l!"));
        }
    }
    private ItemStack giveMilkBasedOnQuality(){

        switch (this.MilkQuality) {

            case Bad:
                return new ItemStack(Material.MILK_BUCKET, 1);
            case Good:
                return new ItemStack(Material.MILK_BUCKET, 2);
            case Very_Good:
                ItemStack item = new ItemStack(Material.MILK_BUCKET, 1);
                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.setDisplayName(ChatUtils.setColorName("&d&lVery Good Cow Milk"));
                List<String> Lore = new ArrayList<>(
                        Arrays.asList(
                                ChatUtils.setColorName("&r"),
                                ChatUtils.setColorName("&f&lFrom: &r" + this.Name))
                );

                meta.setLore(Lore);
                item.setItemMeta(meta);

                return item;
            case Fantasticow:
                ItemStack fantasticow = new ItemStack(Material.MILK_BUCKET, 1);
                ItemMeta fantasticowmeta = fantasticow.getItemMeta();
                fantasticowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                fantasticowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                fantasticowmeta.setDisplayName(ChatUtils.setColorName("&6&lFANTASTICOW"));
                List<String> FantasticowLore = new ArrayList<>(
                        Arrays.asList(
                                ChatUtils.setColorName("&r"),
                                ChatUtils.setColorName("&f&lFrom: &r" + this.Name))
                );

                fantasticowmeta.setLore(FantasticowLore);
                fantasticow.setItemMeta(fantasticowmeta);

                return fantasticow;
            default:
                return new ItemStack(Material.MILK_BUCKET, 3);


        }
    }


}
