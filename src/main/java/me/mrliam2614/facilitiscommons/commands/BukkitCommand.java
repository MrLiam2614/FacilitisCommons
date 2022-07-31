package me.mrliam2614.facilitiscommons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BukkitCommand extends Command {
    private final me.mrliam2614.facilitiscommons.commands.Command executor;

    protected BukkitCommand(@NotNull String name, @NotNull me.mrliam2614.facilitiscommons.commands.Command executor) {
        super(name);
        this.executor = executor;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player) {
            executor.execute((Player) sender, args);
        }
        return false;
    }
}
