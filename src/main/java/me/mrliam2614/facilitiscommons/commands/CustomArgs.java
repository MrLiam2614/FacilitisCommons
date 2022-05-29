package me.mrliam2614.facilitiscommons.commands;

public class CustomArgs {
    public static String[] getCustomArgs(String argOpen, String argClose, String[] arguments) {
        StringBuilder completeCommand = new StringBuilder();
        boolean insideGraph = false;
        int insideArg = 0;
        for (String arg : arguments) {
            completeCommand.append(arg);
            if ((arg.contains(argOpen) && !arg.contains(argClose)) || insideGraph) {
                insideGraph = true;
            }
            if (insideGraph && arg.contains(argClose)) {
                insideGraph = false;
            }

            if (insideGraph) {
                completeCommand.append("_");
                insideArg++;
            } else {
                completeCommand.append(" ");
            }
        }
        String[] args = completeCommand.toString().split(" ");

        if (arguments.length > args.length + insideArg) {
            int len = args.length;
            String[] oldContent = args;
            args = new String[len + 1];

            for (int i = 0; i < oldContent.length; i++) {
                args[i] = oldContent[i];
            }
            args[args.length - 1] = "";
        }

        return args;
    }
}
