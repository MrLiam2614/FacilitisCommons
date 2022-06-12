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

        calcCommand(player, cmd, args);
        return true;
    }

    public void calcCommand(Player player, Command cmd, String[] args) {
        if (cmd != null) {
            if (cmd.getPermission() != null && !player.hasPermission(cmd.getPermission())) {
                //TODO: Handle no permissions
                return;
            }
            String[] arguments = new String[args.length - 1];

            System.arraycopy(args, 1, arguments, 0, args.length - 1);

            Command nextArg = null;
            if (arguments.length > 0) {
                nextArg = cmd.getArgs().stream().filter(arg -> arg.getName().equalsIgnoreCase(arguments[0])).findAny().orElse(null);
            }

            if (nextArg != null) {
                calcCommand(player, nextArg, arguments);
            } else {
                cmd.execute(player, arguments);
            }
        }
    }

    public List<String> calcArgs(Player player, Command cmd, String[] args, List<String> argList) {
        if (argList == null) {
            argList = new ArrayList<>();
        }
        if (cmd != null) {
            if (cmd.getPermission() != null && !player.hasPermission(cmd.getPermission())) {
                //TODO: Handle no permissions
                return argList;
            }
            String[] arguments = new String[args.length - 1];

            System.arraycopy(args, 1, arguments, 0, args.length - 1);

            Command nextArg = null;
            if (arguments.length > 0) {
                nextArg = cmd.getArgs().stream().filter(arg -> arg.getName().equalsIgnoreCase(arguments[0])).findAny().orElse(null);
            }

            if (nextArg != null) {
                calcArgs(player, nextArg, arguments, argList);
            } else {
                for (Command arg : cmd.getArgs()) {
                    argList.add(arg.getName());
                }
            }
        }
        return argList;
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
        if (cmd == null) {
            return nextArgs;
        }
        List<String> getArgs = calcArgs(player, cmd, args, new ArrayList<>());

        for (String gotArg : getArgs) {
            if (gotArg.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                nextArgs.add(gotArg);
        }

        return nextArgs;
    }
}
