package org.example.elwarriorcito.zoofee.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimal;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.AbstractModels.ZooFeeAnimalMilkable;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooFeeAvailableEntityTypes;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooSex;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpawnZooMobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player p = (Player) sender;


        List<String> sexes = Arrays.stream(ZooSex.values())
                .map(ZooSex::name)
                .collect(Collectors.toList());

        int r = new Random().nextInt(sexes.size());

        ZooSex sex = ZooSex.valueOf(sexes.get(r));

        if(strings.length == 1){
            ZooFeeAnimal animal = ZooFeeAvailableEntityTypes.valueOf(strings[0]).label.get();
            animal.Spawn(p.getLocation());
            animal.setSex(sex);
        }

        return true;
    }
}
