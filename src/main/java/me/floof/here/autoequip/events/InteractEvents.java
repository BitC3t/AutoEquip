package me.floof.here.autoequip.events;

import me.floof.here.autoequip.AutoEquip;
import me.floof.here.autoequip.controllers.ArmorController;
import me.floof.here.autoequip.controllers.ArmorType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvents implements Listener {

    private final AutoEquip plugin;

    public InteractEvents(AutoEquip plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void interactMoment(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = event.getItem();

        if(this.plugin.isDisabled()) {
            return;
        }

        if(ArmorType.getArmorType(itemStack.getType()) == null) {
            return;
        }

        if(this.plugin.getArmorController().isOnCache(player)) {
            event.setCancelled(true);
        }

        if(player.getInventory().getItemInMainHand().equals(itemStack)) {
            int slotNum = player.getInventory().getHeldItemSlot();
            this.plugin.getArmorController().replaceArmor(player, itemStack, slotNum);
        } else {
            this.plugin.getArmorController().replaceArmor(player, itemStack);
        }
    }

    @EventHandler
    public void projectileMoment(ProjectileLaunchEvent event) {
        if(!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity().getShooter();

        if(this.plugin.getArmorController().isOnCache(player)) {
            event.setCancelled(true);
        }
    }
}
