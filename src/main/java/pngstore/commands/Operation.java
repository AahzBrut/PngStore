package pngstore.commands;

public enum Operation {
    HELP(new PngHelpCommand()),
    PACK(new PngPackCommand()),
    UNPACK(new PngUnpackCommand());

    private final Command command;

    Operation(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
