package pngstore.commands;

import java.util.Arrays;

public enum Operation {
    HELP(new PngHelpCommand(), null, null),
    PACK(new PngPackCommand(), "-p", "\tpngstore -p <source file> <destination png file>\n"),
    UNPACK(new PngUnpackCommand(), "-u", "\tpngstore -u <source png file> <destination file>\n");

    private final Command command;
    private final String helpText;
    private final String commandToken;

    Operation(Command command, String commandToken, String helpText) {
        this.command = command;
        this.commandToken = commandToken;
        this.helpText = helpText;
    }

    public Command getCommand() {
        return command;
    }

    public String getHelpText() {
        return helpText;
    }

    public static Operation getOperationByCommandToken(String commandToken) {

        return Arrays.stream(Operation.values())
                .filter(operation -> operation.commandToken != null)
                .filter(operation -> operation.commandToken.equalsIgnoreCase(commandToken))
                .findFirst()
                .orElse(HELP);
    }
}
