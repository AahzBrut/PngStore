package pngstore.commands;

import java.nio.file.Path;

public class PngHelpCommand extends PngCommand {

    @Override
    public void execute(Path sourceFile, Path destinationPath) {
        System.out.println("Usage:\n" +
                "\tpngstore -p <source file> <destination png file>\n" +
                "\tpngstore -u <source png file> <destination file>");
    }
}
