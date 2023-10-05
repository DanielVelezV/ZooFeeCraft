package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;

public class ZooGoat extends ZooFeeAnimalMilkable {
    public ZooGoat(EntityType type, Location location, ZooSex Sex, ZooAges Age) {
        super(type,
                location,
                ChatUtils.setColorName(""),
                Sex,
                Age);
    }
}
