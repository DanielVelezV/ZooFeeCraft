package org.example.elwarriorcito.zoofee.Models.CustomMobs.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.jetbrains.annotations.NotNull;

public class AnimalGrowEvent extends Event {

    LivingEntity Entity;
    ZooAges Age;
    private static final HandlerList handlers = new HandlerList();
    public AnimalGrowEvent(LivingEntity entity, ZooAges age) {
        this.Entity = entity;
        this.Age = age;
    }

    public LivingEntity getEntity() {
        return Entity;
    }

    public ZooAges getAge() {
        return Age;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
