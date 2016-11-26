package me.choohan.friend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class fmail implements CommandExecutor{

	main plugin;

	public fmail(main instance) {
		plugin = instance;
		}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		// fmail
				if(args.length == 0){
				List<String> list = plugin.getConfig().getStringList("MHelp");
			    for (String playerlist : list) {
			    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix"))));
			      
			    }
			    return true;
				}
		if (args[0].equalsIgnoreCase("send")){
		if(sender.hasPermission("friend.mail.send")){
		      String message = "";
		      Player p = (Player)sender;
		      if (args.length >= 3)
		      {
		        Player target = Bukkit.getPlayerExact(args[1]);
				if (main.plugin.getPlayerConfig().isSet("Player." + p.getUniqueId() + "." + target.getName())){
		        if (target != null)
		        {
		          for (int i = 2; i < args.length; i++) {
		            message = message + args[i] + " ";
		          }
		          Player player = (Player) sender;
		          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lMessage Sent"));
		          main.plugin.getMailConfig().set("Player." + target.getUniqueId() +"." + message, player.getName());
		          main.plugin.saveDefaultConfig();
			        
			        try {
			           main.plugin.getMailConfig().save(main.plugin.getMailConfigFile());
			        } catch (IOException fpe) {
			           //Whatever you want to print/etc for error purposes
			        }
		        }
		        else
		        {
		          p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("Prefix") + "Target is not online"));
		        }
		      }
		      }
		      
		      else
		      {
					List<String> list = plugin.getConfig().getStringList("MailShort");
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
		} else
			if (args[0].equalsIgnoreCase("list")){
				if(sender.hasPermission("friend.mail.list")){
					Player player = (Player) sender;
							if (main.plugin.getMailConfig().isSet("Player." + player.getUniqueId())){
								Set<String> keys = main.plugin.getMailConfig().getConfigurationSection("Player." + player.getUniqueId()).getKeys(false);
								List<String> list = new ArrayList<String>(keys);
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lMail:"));
							     for (String friends : list) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + friends));
							String author = main.plugin.getMailConfig().getString("Player." + player.getUniqueId() +"." +  friends);
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fBy &a" + author));
							     }
				        } else {
				        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lYou have no mail!"));
				        }
				        
			} else{
				List<String> list = main.plugin.getConfig().getStringList("LackPermission");
			    for (String playerlist : list) {
					Player player = (Player) sender;
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			    }
			}
			} else
				if (args[0].equalsIgnoreCase("clear")){
					if(sender.hasPermission("friend.mail.clear")){
						Player player = (Player) sender;
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lMail Cleared"));
						main.plugin.getMailConfig().set("Player." + player.getUniqueId(), null);
						main.plugin.saveDefaultConfig();
				        
				        try {
				           main.plugin.getMailConfig().save(main.plugin.getMailConfigFile());
				        } catch (IOException fpe) {
				           //Whatever you want to print/etc for error purposes
				        }
					}else {

						List<String> list = main.plugin.getConfig().getStringList("LackPermission");
					    for (String playerlist : list) {
							Player player = (Player) sender;
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
					    }
					}
				} else
					{
						List<String> list = plugin.getConfig().getStringList("MHelp");
					    for (String playerlist : list) {
					    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix"))));
					      
					    }
					    return true;
						}
			return true;

			}
		
}
