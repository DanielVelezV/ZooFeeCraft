package org.example.elwarriorcito.zoofee.Utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.example.elwarriorcito.zoofee.ZooFee;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ZooHolo implements Listener {

    public LivingEntity holder;


    private List<ArmorStand> lines = new ArrayList<>();

    public ZooHolo(LivingEntity Holder){
        this.holder = Holder;
        this.FollowHolder();

        ZooFee.getInstance().getServer().getPluginManager().registerEvents(this, ZooFee.getInstance());
    }

    public void AddLine(String line){
        ArmorStand entity = (ArmorStand) this.holder.getWorld().spawnEntity(this.holder.getLocation(), EntityType.ARMOR_STAND);
        
        entity.setGravity(false);
        entity.setCustomName(line);
        entity.setCustomNameVisible(true);
        entity.setInvulnerable(true);
        entity.setInvisible(true);
        entity.setMarker(true);
        
        lines.add(entity);
        
    }

    public void EditLine(int lineNumber, String line){
        ArmorStand a = lines.get(lineNumber);

        a.setCustomName(line);

    }

    private void FollowHolder(){
        BukkitScheduler scheduler = ZooFee.getInstance().getServer().getScheduler();

        scheduler.scheduleAsyncRepeatingTask(ZooFee.getInstance(), new Runnable() {
            @Override
            public void run() {
                double height = 2;
                for (ArmorStand i : lines){
                    i.teleport(holder.getLocation().add(0, height, 0));
                    height -= 0.3;
                }
            }
        }, 0L, 0L);
    }

    public void removeAll(){
        for (ArmorStand i : lines){
            i.remove();
        }
    }
    public int getLines() {
        return lines.size();
    }

    //region Events
    @EventHandler
    public void onHologramHit(EntityDamageEvent e){
        for(ArmorStand i: lines){
            if (e.getEntity() == i){
                e.setCancelled(true);
            }
        }
    }
    //endregion







}
