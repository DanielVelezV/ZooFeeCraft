package org.example.elwarriorcito.zoofee.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class ZooGUIButton {



    private Function<Player, ItemStack> iconCreator;
    private Consumer<InventoryClickEvent> eventConsumer;

    public ZooGUIButton creator(Function<Player, ItemStack> iconCreator) {
        this.iconCreator = iconCreator;
        return this;
    }

    public ZooGUIButton consumer(Consumer<InventoryClickEvent> consumer){
        this.eventConsumer = consumer;
        return this;
    }
    public Function<Player, ItemStack> getIconCreator() {
        return iconCreator;
    }

    public Consumer<InventoryClickEvent> getEventConsumer() {
        return eventConsumer;
    }

}
