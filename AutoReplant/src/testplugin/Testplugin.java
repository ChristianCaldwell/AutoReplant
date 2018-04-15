package testplugin;

import java.util.Collection;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Testplugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		Block b = (Block) e.getBlock();

		if (b.getType() == Material.CARROT && b.getData() == 7) {
			e.setCancelled(true);
			Collection<ItemStack> drops = b.getDrops();
			for (ItemStack drop : drops) {
				Inventory i = p.getInventory();
				i.addItem(drop);
			}
		} else {
			e.setCancelled(true);
			return;

		}
		ItemStack c = p.getInventory().getItemInMainHand();
		if (c.getType() == Material.CARROT_ITEM) {
			if (c.getAmount() > 1) {
				b.setData(CropState.SEEDED.getData());
				c.setAmount(c.getAmount() - 1);
			} else {
				p.getInventory().remove(c);

			}
		} else {
			b.setType(Material.AIR);
			return;

		}

	}

}
