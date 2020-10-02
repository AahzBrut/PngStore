package pngstore.commands;

import pngstore.PngStoreManager;

import java.nio.file.Path;

public abstract class PngCommand implements Command {

    protected PngStoreManager getPngStoreManager(Path sourceFile, Path destinationFile) {
        return new PngStoreManager(sourceFile, destinationFile);
    }

}
