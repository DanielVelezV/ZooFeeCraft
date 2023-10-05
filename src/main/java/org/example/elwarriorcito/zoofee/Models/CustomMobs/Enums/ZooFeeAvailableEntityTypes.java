package org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums;

import org.bukkit.entity.Pig;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.*;

import java.util.function.Supplier;

public enum ZooFeeAvailableEntityTypes {
    COW(() -> new ZooCow(ZooAges.Baby, ZooSex.Male)),
    GOAT(() -> new ZooGoat(ZooAges.Baby, ZooSex.Male)),
    BUNNY(() -> new ZooBunny(ZooAges.Baby, ZooSex.Male)),
    AXOLOTL(() -> new ZooAxolotl(ZooAges.Baby, ZooSex.Male)),
    PIG(() -> new ZooPig(ZooAges.Baby, ZooSex.Male));

    public final Supplier<ZooFeeAnimal> label;

    ZooFeeAvailableEntityTypes(Supplier<ZooFeeAnimal> label) {
        this.label = label;
    }
}
