package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;

import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;


public class ZooCow extends ZooFeeAnimalMilkable {

    public ZooCow(ZooAges age, ZooSex sex){
        super(EntityType.COW,
                ChatUtils.setColorName("&8&lZoo" + "&f&lCow"),
                sex,
                age);

    }

    public ZooCow(Animals e){
        super(e, ChatUtils.setColorName("&8&lZoo" + "&f&lCow"));

    }












}
