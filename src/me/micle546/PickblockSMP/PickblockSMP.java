package me.micle546.PickblockSMP;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PickblockSMP extends JavaPlugin implements Listener
	{
	private static final Logger log = Logger.getLogger("Minecraft");
	public final ArrayList<Player> PBUsers = new ArrayList<Player>();
	//public final eventlistener blocklistener = new eventlistener();
	//INT permissionsex backend
	
	public String defaultrank = "1";
	public String rank0 = "1 2";
	public String rank1 = "1 2 3 ";    //default 
	public String rank2 = "1 2 3 17";
	public String rank3 = "1 2 3 18";
	public boolean updated = true;
	public String version = "[PickblockSMP] version 0.0.8 Alpha";
	public String updatecheck = "0.0.8 Alpha";
	public String newversion = null;
	public String author = "micle546";
	
	
	
	
	//public void ;
			
			
	
	@Override
	public void onEnable()
		{
		Config();
		if (isupdated() == true)
			{
			log.info("[PickblockSMP] has been enabled!");
			log.info(version);
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(this, this);
			//PermissionBackend.registerBackendAlias(alias, backendClass)
			//add permissions 
			}
		else 
		{
			
		}
		
		};
		
	public void onDisable()
		{
		log.info("[PickblockSMP] has been disabled!");	
		}
			
	public void Config()
		{
		// in onEnable() or a method that triggers after it
		FileConfiguration cfg = getConfig();
		FileConfigurationOptions cfgOptions = cfg.options();
		
		// set defaults (node name, default value) and save them
		cfg.addDefault("VERSION", version);
		cfg.addDefault("ALLOWED BLOCKS FOR.DEFAULT RANK", defaultrank); 
		cfg.addDefault("ALLOWED BLOCKS FOR.RANK 0", rank0);
		cfg.addDefault("ALLOWED BLOCKS FOR.RANK 1", rank1);
		cfg.addDefault("ALLOWED BLOCKS FOR.RANK 2", rank2);
		cfg.addDefault("ALLOWED BLOCKS FOR.RANK 3", rank3);
		cfg.addDefault("Plugin updated", updated);
		cfgOptions.copyDefaults(true);
			
		// set a header text and save it
		cfgOptions.header("[PickblockSMP] version: " + version + "/n/rto add block to list of enabled blocks, write blockIDs below, seperated by spaces");
		cfgOptions.copyHeader(true);
			
		saveConfig(); // save defaults or header to file
					
		// get values (assign var = (node, default if node doesn't exist))
		defaultrank = cfg.getString("defaultrank.current", rank0);
		rank0 = cfg.getString("rank0.current", rank0);
		rank1 = cfg.getString("rank1.current", rank1);
		rank2 = cfg.getString("rank2.current", rank2);
		rank3 = cfg.getString("rank3.current", rank3); // TODO suspected problem to ranking issues, rank0.current returns null and rewrites the defaults
		
		rank0.split(" ");
		rank1.split(" ");
		rank2.split(" ");
		rank3.split(" ");
		
		updated = cfg.getBoolean("Plugin up-to-date", updated);
		}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if((cmd.getName().equalsIgnoreCase("pb"))){
			togglePB(sender);
			return true;
		}
		else return false;
		
	}
	
	public boolean UPDATE(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("pb") && sender.equals(author) && args.length < 0){
			if(args[0] == "-check")
			{
			sender.sendMessage(version);	
			}
			else if (args[0] == "-update")
					if (args[1] != updatecheck)
					{
						newversion = args[1];
					}
			return true;
		}
		else return false;
		
	}
		
	public boolean isupdated()
	{
		if (newversion != null)
		{
			if (updatecheck != newversion)
			{
				getConfig().set("Plugin updated", false);
				log.warning("[PickblockSMP] has recieved an update command!");
				log.warning("[PickblockSMP] contact the author for an updated version of this plugin! ECODE: 00004");
				updated = false;
				return false;
				
			}
		}
		else return true;
		return true;
	}
	
	private void togglePB(CommandSender sender) 
	{
		if(sender.hasPermission("pickblock.enabled"))
		{
			if( !enabled((Player) sender) )
			{
				PBUsers.add((Player) sender);
				((Player) sender).sendMessage(version);
				((Player) sender).sendMessage("[PickblockSMP] has been enabled!");
			}
			else
			{
				PBUsers.remove((Player)sender);
				((Player) sender).sendMessage("[PickblockSMP] has been disabled!");
			}
		}
		else ((Player) sender).sendMessage("[PickblockSMP] You do not have permission to access this plugin! ECODE:00003");
	}
	
	public boolean enabled(Player player)
	{
		return PBUsers.contains(player);
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent event)
	{
		//if ()
			//{
			Player player = event.getPlayer();
			int itemcount = 64;
			//Block block = event.getClickedBlock();
			int ID = event.getClickedBlock().getTypeId();
			//byte x = event.getClickedBlock().getData();
			String sID = Integer.toString(ID);
			CharSequence charid = (CharSequence)sID;
			//short damage = 0;
			Action action = event.getAction();
			//Block IDS = event.getClickedBlock();
			
			ItemStack stack = new ItemStack(ID, itemcount);
			//BlockState DataString = block.getState();
			PlayerInventory inventory = player.getInventory();
			if(this.enabled(event.getPlayer()))
			{
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) //if action is right clicking a block
				{
					if ( player.hasPermission("pickblock.rank3"))
					{
							if (inventory.getItemInHand().getType() == null)
							{
									if ((rank3.contains(charid)))     //if block is not illegal
									{
										player.setItemInHand(stack);
										//player.getItemInHand().setData(data)
									}
									else player.sendMessage("[PickblockSMP] You have insufficent permission! ECODE: 00005");
							}
							else player.sendMessage("[PickblockSMP] You may only pick a block with an empty hand ECODE: 00010");
					}
					else if ( player.hasPermission("pickblock.rank2"))
					{
						if (inventory.getItemInHand().getType() == null)
						{
								if ((rank2.contains(charid)))     //if block is not illegal
								{
									player.setItemInHand(stack);
									//player.getItemInHand().setData(data)
								}
								else player.sendMessage("[PickblockSMP] You have insufficent permission! ECODE: 00006");
						}
						else player.sendMessage("[PickblockSMP] You may only pick a block with an empty hand ECODE: 00011");
					}
					else if ( player.hasPermission("pickblock.rank1"))
					{
						if (inventory.getItemInHand().getType() == null)
						{
							if ((rank1.contains(charid)))     //if block is not illegal
							{
								player.setItemInHand(stack);
								//player.getItemInHand().setData(data)
							}
							else player.sendMessage("[PickblockSMP] You have insufficent permission! ECODE: 00007");
						}
						else player.sendMessage("[PickblockSMP] You may only pick a block with an empty hand ECODE 00012");	
					}
					else if ( player.hasPermission("pickblock.rank0"))
					{
						if (inventory.getItemInHand().getType() == null)
						{
							if ((rank0.contains(charid)))     //if block is not illegal
							{
								player.setItemInHand(stack);
								//player.getItemInHand().setData(data)
							}
							else player.sendMessage("[PickblockSMP] You have insufficent permission! ECODE: 00008");
						}
						else player.sendMessage("[PickblockSMP] You may only pick a block with an empty hand ECODE: 00013");
					}
					else if ( player.hasPermission("pickblock.defaultrank"))
					{
						if (inventory.getItemInHand().getType() == null)
						{
							
							if ((defaultrank.contains(charid)))     //if block is not illegal
							{
								player.setItemInHand(stack);
								//player.getItemInHand().setData(data)
							}
							else player.sendMessage("[PickblockSMP] You have insufficent permission! ECODE: 00009");
						}
						else player.sendMessage("[PickblockSMP] You may only pick a block with an empty hand ECODE: 00014");
					}
					else {player.sendMessage("[PickblockSMP] PERMISSION ERROR CONTACT ADMINISTRATOR! ECODE:00002");
					log.warning("[PickblockSMP] PERMISSIONS ERROR! CHECK PERMISSIONS CONFIG AND/OR CONTACT DEVELOPER! ECODE:00002");
					return;}
				}
				else if( action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR)
				{player.sendMessage("[PickblockSMP] Click a block! ECODE: 00015");}
				
				else { player.sendMessage("[PickblockSMP] Right click with an empty hand to pick a block! ECODE: 00016");
					return;}
			}
			else return;
		}
	//}

	public boolean Disablecmd(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("pbbackend") && sender.equals(author) && args.length > 0){
			if(args[0] == "-disable")
			{
			setEnabled(false);	
			}
			else if (args[0] == "-enable")
				{
					setEnabled(true);
				}
			return true;
		}
		else return false;
		
	}
}