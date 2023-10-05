package org.example.elwarriorcito.zoofee.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.ZooAxolotl;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.ZooBunny;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.ZooFeeCow;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.ZooAnimals.ZooFeePig;
import org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums.ZooAges;
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
        Random r = new Random();

        List<String> sexes = Arrays.stream(ZooSex.values())
                .map(ZooSex::name)
                .collect(Collectors.toList());

        int a = r.nextInt(sexes.size()) + 1;

        ZooSex sex = ZooSex.valueOf(sexes.get(a - 1));

        if(strings[0].equalsIgnoreCase("COW")){
            ZooFeeCow cow = new ZooFeeCow(p.getLocation(), ZooAges.Baby, sex);
        }else if(strings[0].equalsIgnoreCase("PIG")){
            ZooFeePig pig = new ZooFeePig(p.getLocation(), ZooAges.Baby, sex);
        }else if(strings[0].equalsIgnoreCase("AXOLOTL")) {
            ZooAxolotl axolotl = new ZooAxolotl(p.getLocation(), ZooAges.Baby, sex);
        }else if(strings[0].equalsIgnoreCase("BUNNY")) {
            ZooBunny axolotl = new ZooBunny(p.getLocation(), ZooAges.Baby, sex);
        }else{
            ZooFeePig pig = new ZooFeePig(p.getLocation(), ZooAges.Baby, sex);
        }

        return true;
    }
}
