package org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.example.elwarriorcito.zoofee.GUIs.GUIManager;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.GUIs.ZooAnimalStatsGUI;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Events.AnimalGrowEvent;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Interfaces.InventoryHandler;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;
import org.example.elwarriorcito.zoofee.Utils.ZooHolo;
import org.example.elwarriorcito.zoofee.Utils.ZooMobsSerializer;
import org.example.elwarriorcito.zoofee.ZooFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class ZooFeeAnimal implements InventoryHandler, Listener {

    protected EntityType type;
    public String Name;
    public ZooAges Age;
    public ZooSex Sex;
    public int Health, Hunger, Thirst, Stress;
    public int MaxHealth, MaxHunger, MaxThirst, MaxStress;
    protected ZooHolo HolographicName;
    protected ZooAnimalStatsGUI Menu;

    protected Animals entity;
    protected GUIManager menuManager;



    protected boolean isProductive = false;

    public ZooFeeAnimal(EntityType type,
                        String Name,
                        ZooSex Sex,
                        ZooAges Age){
        ZooFee.getInstance().getServer().getPluginManager().registerEvents(this, ZooFee.getInstance());
        this.Sex = Sex;
        this.Age = Age;
        this.Name = Name;
        this.type = type;

        Health      = Hunger = Thirst = Stress = 20;
        MaxHealth   = MaxHunger = MaxThirst = MaxStress = 20;




    }

    public void Spawn(Location location){
        this.entity = (Animals) location.getWorld().spawnEntity(location, this.type);

        this.HolographicName = new ZooHolo((LivingEntity) this.entity);
        this.ShowName();

        if(this.Age.equals(ZooAges.Baby)){
            this.entity.setBaby();
        }
        this.entity.setPersistent(true);
        this.entity.setAgeLock(true);

        this.CalculateStats();
        this.checkOnGrowth();

        this.Menu = new ZooAnimalStatsGUI(this);
        ZooFee.AllAnimals.add(this);
    }

    //region Abstract Methods and Events
    /**
    super: Open Inventory
     */
    @EventHandler
    public void onInteractEvent(PlayerInteractEntityEvent e){
        if(e.getRightClicked() == this.entity && e.getHand().equals(EquipmentSlot.HAND)){
            this.Menu.OpenMenu(e.getPlayer());
        }
    }
    public abstract void onDeathEvent(EntityDeathEvent e);
    public void onGrowEvent(AnimalGrowEvent e){
        if(e.getEntity().equals(this.entity)){
            if(!isProductive && this.Age.equals(ZooAges.Adult)){
                isProductive = true;
            }
        }
    }
    public void CalculateStats(){
        List<Integer> MaxAttributes = new ArrayList<>();
        MaxAttributes.add(this.MaxHealth);
        MaxAttributes.add(this.MaxHunger);
        MaxAttributes.add(this.MaxThirst);

        List<Integer> CurrentAttributes = new ArrayList<>();
        CurrentAttributes.add(this.Health);
        CurrentAttributes.add(this.Hunger);
        CurrentAttributes.add(this.Thirst);



        int MaxAttributesSum = MaxAttributes.stream().mapToInt(Integer::intValue).sum();
        int CurrentAttributesSum = CurrentAttributes.stream().mapToInt(Integer::intValue).sum();

        double StatsPercentaje = (CurrentAttributesSum / (double) MaxAttributesSum) * 100;

        this.Stress = Math.abs((int) StatsPercentaje - 100);

    }
    public void checkOnGrowth(){
        if(this.Age.equals(ZooAges.Old)){
            return;
        }
        Random r = new Random();

        if(r.nextDouble() < 0.5){
            return;
        }

        ZooAges nextAge = ZooAges.values()[this.Age.ordinal() + 1];

        if(nextAge != null){
            this.setAge(nextAge);

            if(!(this.Age.equals(ZooAges.Baby))){
                this.entity.setAdult();
                AnimalGrowEvent e = new AnimalGrowEvent(this.entity, this.Age);
                Bukkit.getPluginManager().callEvent(e);
            }
        }
    }
    //endregion

    //region Utils
    private void ShowName(){
        String sexEmoji = this.Sex == ZooSex.Male ? "&9♂" : "&d♀";
        this.HolographicName.AddLine(this.Name);
        this.HolographicName.AddLine(ChatUtils.setColorName("&4&l" + this.Age.label + "&r (" + sexEmoji + "&r)"));

    }
    public void Remove(){
        this.HolographicName.removeAll();
        this.entity.remove();
    }
    public void Ride(Player player){
        this.entity.setPassenger(player);
    }
    //endregion

    //region Getters and Setters
    public void setName(String name) {
        Name = name;
        this.HolographicName.EditLine(0, ChatUtils.setColorName(name));

    }

    public void setAge(ZooAges age) {
        Age = age;
        String sexEmoji = this.Sex == ZooSex.Male ? "&9♂" : "&d♀";
        this.HolographicName.EditLine(1, ChatUtils.setColorName("&4&l" + this.Age.label + "&r (" + sexEmoji + "&r)"));

    }

    public void setSex(ZooSex sex) {
        Sex = sex;
        String sexEmoji = this.Sex == ZooSex.Male ? "&9♂" : "&d♀";
        this.HolographicName.EditLine(1, ChatUtils.setColorName("&4&l" + this.Age.label + "&r (" + sexEmoji + "&r)"));
    }
    //endregion

    //region Data Persistence
    public ZooMobsSerializer getSerializableDataType(){
        return new ZooMobsSerializer(
                this.entity.getUniqueId().toString(),
                this.Name,
                this.Age.label,
                this.Sex.label
        );
    }
    //endregion

    @Override
    public void onClick(InventoryClickEvent e){

    }

    @Override
    public void onOpen(InventoryOpenEvent e){

    }

    @Override
    public void onClose(InventoryCloseEvent e){

    }
}


