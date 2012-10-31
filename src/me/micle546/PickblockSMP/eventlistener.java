package me.micle546.PickblockSMP;

//import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
//import org.bukkit.event.player.PlayerEvent;
//import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class eventlistener extends PickblockSMP implements Listener
{
	public static PickblockSMP plugin;
	
	
	@EventHandler
	public void interact(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		int itemcount = 64;
		//Block block = event.getClickedBlock();
		int ID = event.getClickedBlock().getTypeId();
		//String sID = Integer.toString(ID);
		//CharSequence charid = (CharSequence)sID;
		//Action action = event.getAction();
		//Block IDS = event.getClickedBlock();
		//ItemStack stack = new ItemStack(ID);
		PlayerInventory inventory = player.getInventory();
		if(this.enabled(event.getPlayer())){
			//if (playerhas) {
					if  (
						(inventory.getItemInHand().getType() == null)
						&&(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))		//if action is right clicking a block
						//&&(rank0.contains(charid))     //if block is not illegal
						//&&(event)
						)
					{
					player.getItemInHand().setTypeId(ID);
					player.getItemInHand().setAmount(itemcount);}
					
				//	else(player.sendMessage("Cannot be done"))
					}


	//		else
	//			if (player.hasPermission(arg10)) {
	//				if  (
	//						//(inventory.getItemInHand().equals(null)
	//						(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))		//if action is right clicking a block
	//						&& (rank1.contains(charid))														//if block is not illegal
	//						);
	//				player.getInventory().addItem(new ItemStack(ID, itemcount));
	//						
	//						
	//		}
	//		else
	//			if(player.hasPermission(arg10));
	//				if  (
	//						//(inventory.getItemInHand().equals(null)
	//						(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))		//if action is right clicking a block
	//						&& (rank2.contains(charid))													//if block is not illegal
	//						);
	//				player.getInventory().addItem(new ItemStack(ID, itemcount));
	//		
			
			

			//else (player.sendMessage("Cannot be done"));	
	
	
	
	}
}