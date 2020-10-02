package pngstore.commands;

import java.nio.file.Path;

public class CommandExecutor {

    private CommandExecutor() {}

    public static void execute(Operation operation, Path sourcePath, Path destinationPath) {
        operation.getCommand().execute(sourcePath, destinationPath);
    }
}
