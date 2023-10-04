package org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.GUIs.ZooAnimalStatsGUI;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;
import org.example.elwarriorcito.zoofee.Utils.ZooHolo;
import org.example.elwarriorcito.zoofee.Utils.ZooMobsSerializer;
import org.example.elwarriorcito.zoofee.ZooFee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class ZooFeeAnimal implements Listener {

    protected EntityType type;
    public String Name;
    public ZooAges Age;
    public ZooSex Sex;
    public int Health = 20, Hunger = 13, Thirst = 20, Stress = 15;
    public int MaxHealth = 20, MaxHunger = 20, MaxThirst = 20, MaxStress;
    protected ZooHolo HolographicName;
    protected ZooAnimalStatsGUI Menu;
    protected Animals entity;
    public ZooFeeAnimal(EntityType type,
                        Location location,
                        String Name,
                        ZooSex Sex,
                        ZooAges Age){

        ZooFee.getInstance().getServer().getPluginManager().registerEvents(this, ZooFee.getInstance());

        this.Sex = Sex;
        this.Age = Age;
        this.Name = Name;
        this.type = type;

        this.entity = (Animals) location.getWorld().spawnEntity(location, this.type);

        if(this.Age.equals(ZooAges.Baby)){
            this.entity.setBaby();
        }

        this.Menu = new ZooAnimalStatsGUI(this);
        this.HolographicName = new ZooHolo((LivingEntity) this.entity);
        this.ShowName();
        this.entity.setPersistent(true);


        ZooFee.AllAnimals.add(this);

        this.CalculateStats();
    }

    //region Abstract Methods
    public abstract void onInteractEvent(PlayerInteractEntityEvent e);
    public abstract void onDeathEvent(EntityDeathEvent e);
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



        System.out.println(this.Stress);



    }

    //endregion
    private void ShowName(){
        String sexEmoji = this.Sex == ZooSex.Male ? "&9♂" : "&d♀";
        this.HolographicName.AddLine(this.Name);
        this.HolographicName.AddLine(ChatUtils.setColorName("&4&l" + this.Age.label + "&r (" + sexEmoji + "&r)"));

    }

    public void Ride(Player player){
        this.entity.setPassenger(player);
    }

    public void Remove(){
        this.HolographicName.removeAll();
        this.entity.remove();
    }

    public ZooMobsSerializer getSerializableDataType(){
        return new ZooMobsSerializer(
                this.entity.getUniqueId().toString(),
                this.Name,
                this.Age.label,
                this.Sex.label
        );
    }

}


