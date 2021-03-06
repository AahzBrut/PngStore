package pngstore.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pngstore.FileUtil.checkFileExist;

public class PngPackCommand extends PngCommand {
    private static final Logger LOGGER = Logger.getLogger(PngPackCommand.class.getName());

    @Override
    public void execute(Path sourceFile, Path destinationPath) {
        checkFileExist(sourceFile);
        var storeManager = getPngStoreManager(sourceFile, destinationPath);
        try {
            storeManager.packFileToPng();
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, ioe.getMessage(), ioe);
        }
    }
}
