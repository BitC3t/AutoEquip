package me.floof.here.autoequip.controllers;

import me.floof.here.autoequip.AutoEquip;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigController {

    private File file;
    private final AutoEquip plugin;

    public ConfigController(AutoEquip plugin) {
        this.plugin = plugin;
    }

    public void init() throws IOException {
        this.file = new File(this.plugin.getDataFolder(), "enabled.yml");

        if(!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }

        if(!this.file.exists()) {
            this.file.createNewFile();

            this.setEnabled(true);
        }

        this.plugin.setDisabled(!(this.getState()));
    }

    public void setEnabled(boolean bool) throws IOException {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        fileConfiguration.set("enabled", bool);

        fileConfiguration.save(this.file);
    }

    public boolean getState() {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        return fileConfiguration.getBoolean("enabled");
    }



}
