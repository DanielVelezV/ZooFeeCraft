package org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooMilkQuality;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Events.AnimalGrowEvent;
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

    public ZooFeeAnimalMilkable(EntityType type, String Name, ZooSex Sex, ZooAges Age) {
        super(type, Name, Sex, Age);

        this.MilkQuality = ZooMilkQuality.Very_Good;
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

    //region Event Handlers
    public void onPlayerMilkEvent(Player player, Entity MilkedEntity){
        if(this.entity.equals(MilkedEntity)){
            if(this.Age.equals(ZooAges.Baby) || this.Age.equals(ZooAges.Young)){
                player.sendMessage(ChatUtils.setColorName("&lThis Cow is too young to be milked!"));
                return;
            }
            if(this.Sex.equals(ZooSex.Male)){
                player.sendMessage(ChatUtils.setColorName("&lYou can't milk a &r&9&lMale"));
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
    @EventHandler
    public void onGrowEvent(AnimalGrowEvent e) {
        super.onGrowEvent(e);

        if(this.isProductive){
            if(this.Sex.equals(ZooSex.Female)){
                if(this.HolographicName.getLines() <= 2){
                    this.HolographicName.AddLine(ChatUtils.setColorName("&a&l!"));
                }
            }
        }
    }
    @EventHandler
    public void onDeathEvent(EntityDeathEvent e) {
        if(e.getEntity().equals(this.entity)){
            this.HolographicName.removeAll();
            e.getDrops().clear();
        }
    }
    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e) {
        if(e.getPlayer().getItemInHand().getType().equals(Material.BUCKET) && e.getHand().equals(EquipmentSlot.HAND)){
            this.onPlayerMilkEvent(e.getPlayer(), e.getRightClicked());
            return;
        }


        super.onInteractEvent(e);
    }
    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent e) {
        if (e.getItemStack().getType() == Material.MILK_BUCKET)
        {
            e.setItemStack(new ItemStack(Material.BUCKET));
            e.getPlayer().updateInventory();
        }
    }
    //endregion

}
