package me.floof.here.autoequip;

import me.floof.here.autoequip.commands.AutoEquipCommand;
import me.floof.here.autoequip.controllers.ArmorController;
import me.floof.here.autoequip.controllers.ConfigController;
import me.floof.here.autoequip.events.InteractEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class AutoEquip extends JavaPlugin {

    private boolean isDisabled = false;
    private ArmorController armorController = new ArmorController();
    private ConfigController configController = new ConfigController(this);

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Controllers
        try {
            configController.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Commands & Events
        this.enableCommands();
        this.enableEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isDisabled() {
        return this.isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;

        try {
            this.configController.setEnabled(!isDisabled);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArmorController getArmorController() {
        return this.armorController;
    }

    public void enableEvents() {
        Bukkit.getPluginManager().registerEvents(new InteractEvents(this), this);
    }

    public void enableCommands() {
        this.getCommand("auto-equip").setExecutor(new AutoEquipCommand(this));
        this.getCommand("auto-equip").setTabCompleter(new AutoEquipCommand(this));
    }
}
