package org.example.elwarriorcito.zoofee.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {

    public static ItemStack BuildItem(String Name,
                                      Material ItemMaterial,
                                      int Quantity,
                                      @Nullable List<String> Lore,
                                      @Nullable Boolean Glow){

        ItemStack a = new ItemStack(ItemMaterial, Quantity);
        ItemMeta meta = a.getItemMeta();

        meta.setDisplayName(ChatUtils.setColorName(Name));

        if(Lore != null){
            meta.setLore(Lore.stream().map(b -> ChatUtils.setColorName(b)).toList());
        }

        if(Glow != null){
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        }

        a.setItemMeta(meta);
        return a;


    }


}
