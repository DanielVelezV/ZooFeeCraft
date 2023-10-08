package org.example.elwarriorcito.zoofee;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.elwarriorcito.zoofee.Commands.SpawnZooMobCommand;
import org.example.elwarriorcito.zoofee.Commands.SpawnZooMobTabCompleter;
import org.example.elwarriorcito.zoofee.GUIs.GUIListener;
import org.example.elwarriorcito.zoofee.GUIs.GUIManager;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.ZooCow;
import org.example.elwarriorcito.zoofee.Utils.ZooMobsSerializer;

import java.util.ArrayList;
import java.util.List;

public final class ZooFee extends JavaPlugin implements Listener {
    public static ZooFee instance = null;
    public static List<ZooFeeAnimal> AllAnimals = new ArrayList<>();
    private int growTaskId;

    public static GUIManager guiManager;

    @Override
    public void onEnable(){
        instance = this;
        this.getCommand("ZooSpawn").setExecutor(new SpawnZooMobCommand());
        this.getCommand("ZooSpawn").setTabCompleter(new SpawnZooMobTabCompleter());

        guiManager = new GUIManager();
        GUIListener guiListener = new GUIListener(guiManager);

        this.getServer().getPluginManager().registerEvents(guiListener, this);
        this.getServer().getPluginManager().registerEvents(this, this);

        growTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::TryGrow, 0, 20 * 5);


    }

    @Override
    public void onDisable() {
        for (ZooFeeAnimal a : AllAnimals){
            a.HolographicName.removeAll();
        }
        Bukkit.getScheduler().cancelTask(growTaskId);


    }

    public static ZooFee getInstance(){
        return instance;
    }

    public void TryGrow(){
        for (ZooFeeAnimal a : AllAnimals){
            a.checkOnGrowth();
        }
    }

    public static GUIManager getGuiManager(){
        return guiManager;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e){
        var Entities = e.getChunk().getEntities();

        for (int i = 0; i < Entities.length; i++) {
            if(Entities[i].getType() == EntityType.COW){
                ZooCow f = new ZooCow((Animals) Entities[i]);
            }
        }
    }







    // PlayerCompletedSpawnEvent e
    // if(e.Player().getGameStatus() == Status.SPECTATING)
}
