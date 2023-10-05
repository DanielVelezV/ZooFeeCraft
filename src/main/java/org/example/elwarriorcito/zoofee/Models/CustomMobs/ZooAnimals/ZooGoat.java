package org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;
import org.example.elwarriorcito.zoofee.Utils.ChatUtils;

public class ZooGoat extends ZooFeeAnimalMilkable {
    public ZooGoat(ZooAges Age, ZooSex Sex) {
        super(EntityType.GOAT,
                ChatUtils.setColorName("&7&lZoo&r&f&lGoat"),
                Sex,
                Age);
    }
}
