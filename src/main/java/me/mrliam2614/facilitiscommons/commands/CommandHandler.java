package me.mrliam2614.facilitiscommons.commands;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandHandler implements CommandExecutor, TabCompleter {

    @Getter
    private List<Command> commandList;

    public CommandHandler() {
        this.commandList = new ArrayList<>();
    }

    public void registerCommand(Command command) {
        this.commandList.add(command);
    }

    public void addArg(Command parentCommand, Command arg) {
        parentCommand.addArg(arg);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return true;
        }

        String commandName = args[0];

        Command cmd = commandList.stream().filter(c -> c.getName().equalsIgnoreCase(commandName)).findAny().orElse(null);

        if (cmd != null) {
            if (cmd.getPermission() != null && !player.hasPermission(cmd.getPermission())) {
                //TODO: Handle no permissions
                return true;
            }
            String[] arguments = new String[args.length - 1];

            System.arraycopy(args, 1, arguments, 0, args.length - 1);

            Command nextArg = cmd.getArgs().stream().filter(arg -> arg.getName().equalsIgnoreCase(arguments[0])).findAny().orElse(null);

            if (nextArg != null) {
                nextArg.execute(player, arguments);
            } else {
                cmd.execute(player, arguments);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        List<String> nextArgs = new ArrayList<>();
        if (!(sender instanceof Player)) {
            return nextArgs;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return nextArgs;
        }

        String commandName = args[0];
        Command cmd = commandList.stream().filter(c -> c.getName().equalsIgnoreCase(commandName)).findAny().orElse(null);

        if (args.length == 1) {
            List<Command> allCommands = commandList.stream().filter(c -> c.getName().contains(commandName)).collect(Collectors.toList());
            for (Command cmda : allCommands) {
                nextArgs.add(cmda.getName());
            }
            return nextArgs;
        }

        String[] arguments = new String[args.length - 1];
        System.arraycopy(args, 1, arguments, 0, args.length - 1);

        if (cmd == null) {
            return nextArgs;
        }
        Command lastArg = cmd, prevArg = cmd;
        for(int x = 0; x< arguments.length; x++){
            int finalX = x;
            prevArg = lastArg;
            lastArg = lastArg.getArgs().stream().filter(arg -> arg.getName().equalsIgnoreCase((arguments[finalX]))).findAny().orElse(null);
        }

        if (lastArg != null) {
            if (lastArg.getPermission() != null && !player.hasPermission(lastArg.getPermission())) {
                return nextArgs;
            }
            if (lastArg.getArgs() != null) {
                player.sendMessage(lastArg.getArgs().toString());
                for (Command arg : lastArg.getArgs()) {
                    nextArgs.add(arg.getName());
                }
            }
        }
        return nextArgs;
    }

    public boolean validateArg(String argName, Command cmd) {
        Command rem = cmd.getArgs().stream().filter(c -> c.getName().equalsIgnoreCase(argName)).findAny().orElse(null);
        if (rem == null) {
            return false;
        }
        return true;
    }
}
