package org.example.elwarriorcito.zoofee;

import com.google.gson.Gson;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.elwarriorcito.zoofee.Commands.SpawnZooMobCommand;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Utils.ZooMobsSerializer;

import java.util.ArrayList;
import java.util.List;

public final class ZooFee extends JavaPlugin implements Listener {
    public static ZooFee instance = null;
    public static List<ZooFeeAnimal> AllAnimals = new ArrayList<>();

    @Override
    public void onEnable(){
        instance = this;
        this.getCommand("ZooSpawn").setExecutor(new SpawnZooMobCommand());
        this.getServer().getPluginManager().registerEvents(this, this);


    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e){
        System.out.println(e.getRightClicked().getUniqueId());
    }
    @Override
    public void onDisable() {
        Gson gson = new Gson();
        List<ZooMobsSerializer> a = new ArrayList<>();
        for (ZooFeeAnimal b : AllAnimals){
            a.add(b.getSerializableDataType());
        }

        String serielized = gson.toJson(a);

        System.out.println(serielized);
//
    }

    public static ZooFee getInstance(){
        return instance;
    }

}
