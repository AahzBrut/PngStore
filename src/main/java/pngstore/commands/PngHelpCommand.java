package pngstore.commands;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class PngHelpCommand extends PngCommand {

    @Override
    @SuppressWarnings("java:S106")
    public void execute(Path sourceFile, Path destinationPath) {
        System.out.println("Usage:\n");

        Arrays.stream(Operation.values())
                .map(Operation::getHelpText)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }
}
