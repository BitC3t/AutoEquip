package me.floof.here.autoequip.commands;

import me.floof.here.autoequip.AutoEquip;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.tools.jconsole.Tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoEquipCommand implements CommandExecutor, TabCompleter {
    private final AutoEquip plugin;
    private List<String> autoComplete = Arrays.asList("disable", "enable", "info");
    private char pencil = '\u270E';

    public AutoEquipCommand(AutoEquip plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("auto-equip")) {
            if(!(sender instanceof Player)) {
                return true;
            }

            if(!sender.isOp()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis can only be executed as an operator."));
                return true;
            }

            if(args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cArguments invalid : &f/auto-equip [disable/enable/info]"));
                return true;
            }

            Player player = (Player) sender;

            this.execute(player, args);
        }

        return true;
    }

    private void execute(Player player, String[] args) {
        switch(args[0]) {
            case "disable":
                this.plugin.setDisabled(true);
                player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&7[&b" + this.pencil + "&7] &fAutoEquip is now &cdisabled.")
                );
                break;
            case "enable":
                this.plugin.setDisabled(false);
                player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&7[&b" + this.pencil + "&7] &fAutoEquip is now &aenabled.")
                );
                break;
            case "info":
                if(this.plugin.isDisabled()) {
                    player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', "&7[&b" + this.pencil + "&7] &fAutoEquip is currently &cdisabled. &fUse /auto-equip enable to enable the plugin.")
                    );
                } else {
                    player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', "&7[&b" + this.pencil + "&7] &fAutoEquip is currently &aenabled. &fUse /auto-equip disable to disable the plugin.")
                    );
                }

                break;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        for(String keys : autoComplete) {
            tempList.add(keys);
        }

        StringUtil.copyPartialMatches(args[0], tempList, completions);
        Collections.sort(completions);

        return completions;
    }
}
