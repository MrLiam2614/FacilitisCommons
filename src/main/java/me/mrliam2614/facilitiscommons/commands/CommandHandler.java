package me.mrliam2614.facilitiscommons.commands;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.server.TabCompleteEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    @Getter
    private List<Command> commandList;

    public CommandHandler(){
        this.commandList = new ArrayList<>();
    }

    public void registerCommand(Command command){
        this.commandList.add(command);
    }
    public void addArg(Command parentCommand, Command arg){
        parentCommand.addArg(arg);
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

            for(Command arg : cmd.getArgs()){
                if(arg.getName().equalsIgnoreCase(commandName)){
                    arg.execute(player, arguments);
                    return true;
                }
            }
            cmd.execute(player, arguments);
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        List<String> nextArgs = new ArrayList<>();
        if(!(sender instanceof Player)) {
            return null;
        }

        Player player = (Player) sender;

        if(args.length < 1) {
            return null;
        }

        String commandName = args[args.length - 1];

        Command cmd = commandList.stream().filter(c -> c.getName().equalsIgnoreCase(commandName)).findAny().orElse(null);

        if(cmd != null) {
            if(cmd.getPermission() != null && !player.hasPermission(cmd.getPermission())) {
                //TODO: Handle no permissions
                return null;
            }
            for(Command arg : cmd.getArgs()){
                nextArgs.add(arg.getName());
            }
        }
        return null;
    }
}
