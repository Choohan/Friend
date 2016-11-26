package me.choohan.friend;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fmsg implements CommandExecutor{

	main plugin;

	public fmsg(main instance) {
		plugin = instance;
		}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {

		if(sender.hasPermission("friend.msg")){
	      String message = "";
	      Player p = (Player)sender;
	      if (args.length >= 2)
	      {
	        Player target = Bukkit.getPlayerExact(args[0]);
			if (main.plugin.getPlayerConfig().isSet("Player." + p.getUniqueId() + "." + target.getName())){
	        if (target != null)
	        {
	          for (int i = 1; i < args.length; i++) {
	            message = message + args[i] + " ";
	          }
	          p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("Prefix") + "&6You &f-> &a" + target.getName() + " &f: &7 " + message));
	          target.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("Prefix") + "&6"+ p.getName() + " &f-> &ayou " + " &f: &7 " + message));
	        }
	        else
	        {
	          p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("Prefix") + "Target is not online"));
	        }
			}
			else {

				List<String> list = plugin.getConfig().getStringList("NotFriend");
			    for (String playerlist : list) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
			    
			}
	      }
	      }
	      
	      else
	      {
				List<String> list = plugin.getConfig().getStringList("MSGShort");
			    for (String playerlist : list) {
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			    }
	      }
	      
	      }else{

			List<String> list = main.plugin.getConfig().getStringList("LackPermission");
		    for (String playerlist : list) {
				Player player = (Player) sender;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			
		    }
	      }
		return true;

		}
}
