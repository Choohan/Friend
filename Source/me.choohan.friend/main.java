package me.choohan.friend;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public class main extends JavaPlugin{
	
		// Permission
	
		// Permission to add friend to friend list
	  public Permission addfriend = new Permission("friend.add");
		// Permission to add friend to friend list
	  public Permission friendlist = new Permission("friend.list");
	  	// Permission to msg all the friend
	  public Permission msg = new Permission("friend.msg");
	  	// Get Friend's position X Y Z
	  public Permission getpos = new Permission("friend.getpos");
	  	// Teleport to a friend
	  public Permission teleport = new Permission("friend.teleport");
	  	// Send mail
	  public Permission sendmail = new Permission("friend.mail.send");
	  	// Send mail
	  public Permission listmail = new Permission("friend.mail.list");
	  public Permission friendgift = new Permission("friend.gift");
	  public Permission clearmail = new Permission("friend.mail.clear");
	  public Permission rmfriend = new Permission("friend.remove");
	  public Permission homep = new Permission("friend.home");
	  public Permission sethome = new Permission("friend.sethome");
	  
	  // File Configuration and creation define
	  private FileConfiguration player;
	  private File playerf;
	  private FileConfiguration mail;
	  private File mailf;
	  private FileConfiguration home;
	  private File homef;
	  public static main plugin;
	  
	  // When this plugin is enabled
	  public void onEnable()
	  {
		  // Define the plugin
		  plugin = this;
		  
		  // Create the file
	      createFiles();

	      // Plugin Manager
	      PluginManager pm = getServer().getPluginManager();
	      pm.addPermission(this.addfriend);
	      pm.addPermission(this.msg);
	      pm.addPermission(this.getpos);
	      pm.addPermission(this.teleport);
	      pm.addPermission(this.friendlist);
	      pm.addPermission(this.friendgift);
	      pm.addPermission(this.listmail);
	      pm.addPermission(this.sendmail);
	      pm.addPermission(this.clearmail);
	      pm.addPermission(this.rmfriend);
	      pm.addPermission(this.homep);
	      pm.addPermission(this.sethome);

	      // Enable Message
	      getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix") + " &6Friend &ahad been enabled!"));
	      
	      // Command Register
	      registercommands();
	      
	      // Create Config.yml
	      if (!new File(getDataFolder(), "config.yml").exists()) {
	    	  getConfig().options().copyDefaults(true);
	      }
	      saveDefaultConfig();
	      
	  }
	  
	  private void registercommands() {
		    getCommand("friend").setExecutor(new command(this));
		    getCommand("fmsg").setExecutor(new fmsg(this));
		    getCommand("fmail").setExecutor(new fmail(this));
		
	}

	  public FileConfiguration getPlayerConfig() {
	      return this.player;
	  }
	  public FileConfiguration getMailConfig() {
		  return this.mail;
	  }
	  public FileConfiguration getHomeConfig(){
		  return this.home;
	  }
	private void createFiles() {
	     
		  // Define player.yml
	      playerf = new File(getDataFolder(), "player.yml");
	      
	      // Define mail.yml
	      mailf = new File(getDataFolder(), "mail.yml");
	      
	      // Define home.yml
	      homef = new File(getDataFolder(), "home.yml");

	      // Create player.yml
	      if(!playerf.exists()) {
	          playerf.getParentFile().mkdirs();
	          saveResource("player.yml", false);
	      }
	      // create mail.yml
	      if(!mailf.exists()){
	    	  mailf.getParentFile().mkdirs();
	    	  saveResource("mail.yml", false);
	      }
	      
	      // Create home.yml
	      if (!homef.exists()){
	    	  homef.getParentFile().mkdirs();
	    	  saveResource("home.yml", false);
	      }
	      
	      // Load player.yml
	      player = new YamlConfiguration();
	      try{
	          player.load(playerf);
	      } catch (IOException e){
	          e.printStackTrace();
	      } catch (InvalidConfigurationException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	      
	      // Load mail.yml
	      mail = new YamlConfiguration();
	      try{
	    	  mail.load(mailf);
	      } catch (IOException e){
	    	  e.printStackTrace();
	      } catch (InvalidConfigurationException e){
	    	  e.printStackTrace();
	      }
	      
	      // load home.yml
	      home = new YamlConfiguration();
	      try{
	    	  home.load(homef);
	      } catch (IOException e){
	    	  e.printStackTrace();
	      } catch (InvalidConfigurationException e){
	    	  e.printStackTrace();
	      }
	      
	}
	  public File getPlayerConfigFile() {
	      return this.playerf;
	  }
	  
	  public File getMailConfigFile(){
		  return this.mailf;
	  }
	  
	  public File getHomeConfigFile(){
		  return this.homef;
	  }

	// When the plugin disabled
	  public void onDisable(){

		  // Disable Message
		  getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix") + " &6Friend &ahad been disabled!"));
	   
		  saveDefaultConfig();
		  
		  // Prevent memmory from leeking
		  plugin = null;
	  }
}
