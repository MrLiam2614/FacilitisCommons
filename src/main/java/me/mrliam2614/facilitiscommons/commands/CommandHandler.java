package me.mrliam2614.facilitiscommons.commands;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor {

    @Getter
    private List<Command> commandList;

    public CommandHandler(){
        this.commandList = new ArrayList<>();
    }

    public void registerCommand(Command command){
        this.commandList.add(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player");
            return true;
        }

        Player player = (Player) sender;

        if(args.length < 1) {
            return true;
        }

        String commandName = args[0];

        Command cmd = commandList.stream().filter(c -> c.getName().equalsIgnoreCase(commandName)).findAny().orElse(null);
        if(cmd != null) {
            if(cmd.getPermission() != null && !player.hasPermission(cmd.getPermission())) {
                //TODO: Handle no permissions
                return true;
            }
            String[] arguments = new String[args.length-1];


            System.arraycopy(args, 1, arguments, 0, args.length - 1);

            cmd.execute(player, arguments);
        }
        return true;
    }
}
