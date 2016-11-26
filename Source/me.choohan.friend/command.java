package me.choohan.friend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.ItemStack;



public class command implements CommandExecutor{

	main plugin;

	public command(main instance) {
		plugin = instance;
		}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		
		// friend
		if(args.length == 0){
		List<String> list = plugin.getConfig().getStringList("Help");
	    for (String playerlist : list) {
	    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix")).replaceAll("<player>", sender.getName())));
	      
	    }
	    return true;
		}
		
		// friend add
		if (args[0].equalsIgnoreCase("add") ) {
			if(sender.hasPermission("friend.add")){
			Player target = Bukkit.getPlayerExact(args[1]);
			Player player = (Player) sender;
			if(target == null){
				List<String> list = plugin.getConfig().getStringList("ErrorToAdd");
			    for (String playerlist : list) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			    }
			   }else{
				   if (args.length == 1){
					   

						List<String> list = plugin.getConfig().getStringList("AddShort");
					    for (String playerlist : list) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
					    }
				   }else{
				   }

						main.plugin.getPlayerConfig().set("Player." + player.getUniqueId() + "." + target.getName(), "friend");
						main.plugin.getPlayerConfig().set("Player." + target.getUniqueId() + "." + player.getName(), "friend");
						main.plugin.saveDefaultConfig();
				        
				        try {
				           main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
				        } catch (IOException fpe) {
				           //Whatever you want to print/etc for error purposes
				        }
						List<String> list = plugin.getConfig().getStringList("Add");
					    for (String playerlist : list) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("Prefix") + playerlist.replaceAll("<target>", target.getName())));	   
					    }
					}
			   }
		 else{
			List<String> list = plugin.getConfig().getStringList("LackPermission");
		    for (String playerlist : list) {
				Player player = (Player) sender;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			
		}
		}
		} else
			if (args[0].equalsIgnoreCase("list")){
				if(sender.hasPermission("friend.list")) {
					Player player = (Player) sender;
					if (main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId())){
						Set<String> keys = main.plugin.getPlayerConfig().getConfigurationSection("Player." + player.getUniqueId()).getKeys(false);
						List<String> list = new ArrayList<String>(keys);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lFriend:"));
					     for (String friends : list) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + friends));
					     }
					}else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lYou have no friend!"));
					}
					} else{
				Player player = (Player) sender;
				List<String> list = plugin.getConfig().getStringList("LackPermission");
			    for (String playerlist : list) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
			    }
			}
			} else 
				if (args[0].equalsIgnoreCase("getpos")){
					if (sender.hasPermission("friend.getpos")){
						if (args.length == 1){
							Player player = (Player) sender;
							List<String> list = plugin.getConfig().getStringList("GetPosShort");
						    for (String playerlist : list) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
						    }
						}else{
							Player target = Bukkit.getPlayerExact(args[1]);
							Player player = (Player) sender;
						if (main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId() + "." + target.getName())){

							double locationX = target.getLocation().getX();
							double locationY = target.getLocation().getY();
							double locationZ = target.getLocation().getZ();
							String x = String.valueOf(locationX);
							String y = String.valueOf(locationY);
							String z = String.valueOf(locationZ);

							List<String> list = plugin.getConfig().getStringList("GetPos");
						    for (String playerlist : list) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<player>", target.getName()).replaceAll("<x>", x).replaceAll("<y>", y).replaceAll("<z>", z)));
						    }
						} else {
							List<String> list = plugin.getConfig().getStringList("NotFriend");
						    for (String playerlist : list) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
						    }
						    }
						}
						
					} else {
						Player player = (Player) sender;
						List<String> list = plugin.getConfig().getStringList("LackPermission");
					    for (String playerlist : list) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
					    }
				}
				} else
					if (args[0].equalsIgnoreCase("tp")){
						if (sender.hasPermission("friend.teleport")){
							if (args.length == 1){
								Player player = (Player) sender;
								List<String> list = plugin.getConfig().getStringList("TPShort");
							    for (String playerlist : list) {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
							    }
							}else{
								Player target = Bukkit.getPlayerExact(args[1]);
								Player player = (Player) sender;
							if (main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId() + "." + target.getName())){

								Location tlocation = target.getLocation();
								player.teleport(tlocation);
								List<String> list = plugin.getConfig().getStringList("Teleport");
							    for (String playerlist : list) {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getDisplayName())));
							    
							    }
							} else {
								List<String> list = plugin.getConfig().getStringList("NotFriend");
							    for (String playerlist : list) {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
							    }
								
							}
							}
						}else {
							Player player = (Player) sender;
							List<String> list = plugin.getConfig().getStringList("LackPermission");
						    for (String playerlist : list) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
						    }
						
					}
					}else
						if (args[0].equalsIgnoreCase("gift")){
							if(sender.hasPermission("friend.gift")){
								if(args.length == 1){
									Player player = (Player) sender;
									List<String> list = plugin.getConfig().getStringList("GiftShort");
								    for (String playerlist : list) {
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
								    }
								    }else 
								    {
									
								    	Player target = Bukkit.getPlayerExact(args[1]);
								    	Player player = (Player) sender;
								    	if(main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId() + "." + target.getName())){
								    		if(target != null){
								    		Player p = (Player) sender;
								    		org.bukkit.inventory.ItemStack pitem = p.getInventory().getItemInMainHand();
								    		target.getInventory().addItem(pitem);
								    		player.getInventory().remove(pitem);
								    		target.sendMessage(ChatColor.translateAlternateColorCodes('&', "Recieved gift from " + player.getDisplayName()));
								    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Sent gift to " + target.getDisplayName()));
										    }else {
										    	player.sendMessage("Target player not online");
										    }
								    	}else{

											List<String> list = plugin.getConfig().getStringList("NotFriend");
										    for (String playerlist : list) {
											player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
										    }
								    	}
								    }
							} else {
								Player player = (Player) sender;
								List<String> list = plugin.getConfig().getStringList("LackPermission");
							    for (String playerlist : list) {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
							    }
								
							}
						} else
							if (args[0].equalsIgnoreCase("remove")){
								if (sender.hasPermission("friend.remove")){
									Player player = (Player) sender;
									Set<String> keys = main.plugin.getPlayerConfig().getConfigurationSection("Player." + player.getUniqueId()).getKeys(false);
									List<String> list = new ArrayList<String>(keys);
									Player target = Bukkit.getPlayerExact(args[1]);
									if (target != null){
										if(main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId() + "." + target.getName())){
											player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Removed " + target.getName() + "from friend list"));
											main.plugin.getPlayerConfig().set("Player." + player.getUniqueId() + "." + target.getName(), null);
											main.plugin.getPlayerConfig().set("Player." + target.getUniqueId() + "." + player.getName(), null);
											try {
										           main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
										        } catch (IOException fpe) {
										           //Whatever you want to print/etc for error purposes
										        }
										}else {

											List<String> plist = plugin.getConfig().getStringList("NotFriend");
										    for (String playerlist : plist) {
											player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
										    }
										}
									}else{
								    	player.sendMessage("Target player not online");
										
									}
									
								}else {

									Player player = (Player) sender;
									List<String> list = plugin.getConfig().getStringList("LackPermission");
								    for (String playerlist : list) {
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
								    }
								}
							} else
								if (args[0].equalsIgnoreCase("sethome")){
									
									if (sender.hasPermission("friend.sethome")){
										Player player = (Player) sender;
										Location plocation = player.getLocation();
										String plocate = String.valueOf(plocation);
										player.sendMessage("Added home");
										main.plugin.getHomeConfig().set("Player." + player.getUniqueId() + ".world", player.getLocation().getWorld().getName());
										main.plugin.getHomeConfig().set("Player." + player.getUniqueId() + ".x", player.getLocation().getX());
										main.plugin.getHomeConfig().set("Player." + player.getUniqueId() + ".y", player.getLocation().getY());
										main.plugin.getHomeConfig().set("Player." + player.getUniqueId() + ".z", player.getLocation().getZ());
										main.plugin.saveDefaultConfig();
										try {
									           main.plugin.getHomeConfig().save(main.plugin.getHomeConfigFile());
									        } catch (IOException fpe) {
									           //Whatever you want to print/etc for error purposes
									        }
									} else {


										Player player = (Player) sender;
										List<String> list = plugin.getConfig().getStringList("LackPermission");
									    for (String playerlist : list) {
										player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
									    }
									}
									
								}else
									if (args[0].equalsIgnoreCase("home")){
										if(sender.hasPermission("friend.home")){
											if(args.length == 1){
												Player player = (Player) sender;
												List<String> list = main.plugin.getConfig().getStringList("HomeShort");
											    for (String playerlist : list) {
												player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
											    }
											    } else {
											Player player = (Player) sender;
											Player target = Bukkit.getPlayerExact(args[1]);
											if (target != null){
												if(main.plugin.getPlayerConfig().isSet("Player." + player.getUniqueId() + "." + target.getName())){
													if(!main.plugin.getHomeConfig().isSet("Player." + target.getUniqueId())){

														List<String> plist = plugin.getConfig().getStringList("NoHome");
													    for (String playerlist : plist) {
														player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
													    }
													}else {
													Location loc = new Location(
															Bukkit.getServer().getWorld(main.plugin.getHomeConfig().getString("Player." + target.getUniqueId() + ".world")),
															main.plugin.getHomeConfig().getDouble("Player." + target.getUniqueId() + ".x"),
															main.plugin.getHomeConfig().getDouble("Player." + target.getUniqueId() + ".y"),
															main.plugin.getHomeConfig().getDouble("Player." + target.getUniqueId() + ".z"));
													player.teleport(loc);
													List<String> plist = plugin.getConfig().getStringList("TpHome");
												    for (String playerlist : plist) {
													player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
												    }
												    }
												   } else {

													List<String> plist = plugin.getConfig().getStringList("NotFriend");
												    for (String playerlist : plist) {
													player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<target>", target.getName())));
												    }
												}
											}else {

										    	player.sendMessage("Target player not online");
											}
											    }
										}else {

											Player player = (Player) sender;
											List<String> list = plugin.getConfig().getStringList("LackPermission");
										    for (String playerlist : list) {
											player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist));
										    }
										}
									}else
								{

								List<String> list = plugin.getConfig().getStringList("Help");
							    for (String playerlist : list) {
							    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix")).replaceAll("<player>", sender.getName())));
							      
							    }
							}
					
		return false;
	}

}

