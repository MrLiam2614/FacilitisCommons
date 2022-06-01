package me.mrliam2614.facilitiscommons.commands;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private String name;
    private String description;
    private String permission;
    private List<Command> args;

    protected Command() {
        loadProps();
        args = new ArrayList<>();
    }

    private void loadProps() {
        CommandMeta commandMeta = this.getClass().getAnnotation(CommandMeta.class);

        if (commandMeta == null) {
            return;
        }

        this.name = commandMeta.name();
        this.description = commandMeta.description();
        this.permission = commandMeta.permission().isBlank() ? null : commandMeta.permission();
    }


    public abstract void execute(Player player, String[] arguments);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public void addArg(Command cmd){
        args.add(cmd);
    }

    public List<Command> getArgs(){
        return args;
    }
}