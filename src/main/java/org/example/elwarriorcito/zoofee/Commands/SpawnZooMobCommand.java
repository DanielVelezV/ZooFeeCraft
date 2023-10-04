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

public class SpawnZooMobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player p = (Player) sender;

        if(strings[0].equalsIgnoreCase("cow")){
            ZooFeeCow cow = new ZooFeeCow(p.getLocation(), ZooAges.Adult, ZooSex.Male);
        }else if(strings[0].equalsIgnoreCase("pig")){
            ZooFeePig pig = new ZooFeePig(p.getLocation(), ZooAges.Baby, ZooSex.Female);
        }else if(strings[0].equalsIgnoreCase("axolotl")) {
            ZooAxolotl axolotl = new ZooAxolotl(p.getLocation(), ZooAges.Baby, ZooSex.Female);
        }else if(strings[0].equalsIgnoreCase("bunny")) {
            ZooBunny axolotl = new ZooBunny(p.getLocation(), ZooAges.Baby, ZooSex.Female);
        }else{
            ZooFeePig pig = new ZooFeePig(p.getLocation(), ZooAges.Baby, ZooSex.Female);
        }

        return true;
    }
}
