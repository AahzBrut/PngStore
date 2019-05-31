package pngstore.commands;

import pngstore.PngStoreManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PngUnpackCommand extends PngCommand {
	private static final Logger LOGGER = Logger.getLogger(PngUnpackCommand.class.getName());

	@Override
	public void execute(Path sourceFile, Path destinationFile) {
		PngStoreManager storeManager = getPngStoreManager(sourceFile, destinationFile);
		try {
			storeManager.unpackPngToFile();
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.getMessage(), ioe);
		}
	}
}
