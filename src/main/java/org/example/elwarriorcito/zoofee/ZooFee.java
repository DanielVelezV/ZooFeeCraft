package org.example.elwarriorcito.zoofee;

import com.google.gson.Gson;
import com.mojang.datafixers.TypeRewriteRule;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.elwarriorcito.zoofee.Commands.SpawnZooMobCommand;
import org.example.elwarriorcito.zoofee.Commands.SpawnZooMobTabCompleter;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Utils.ZooMobsSerializer;

import java.util.ArrayList;
import java.util.List;

public final class ZooFee extends JavaPlugin{
    public static ZooFee instance = null;
    public static List<ZooFeeAnimal> AllAnimals = new ArrayList<>();

    private int growTaskId;

    @Override
    public void onEnable(){
        instance = this;
        this.getCommand("ZooSpawn").setExecutor(new SpawnZooMobCommand());
        this.getCommand("ZooSpawn").setTabCompleter(new SpawnZooMobTabCompleter());

        growTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::TryGrow, 0, 20 * 60);

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

        Bukkit.getScheduler().cancelTask(growTaskId);
//
    }

    public static ZooFee getInstance(){
        return instance;
    }

    public void TryGrow(){
        for (ZooFeeAnimal a : AllAnimals){
            a.checkOnGrowth();
        }
    }

}
