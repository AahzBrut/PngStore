package pngstore;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

	private FileUtil() {}

	public static void checkFileExist(Path srcFile) {
		if (!srcFile.toFile().exists() || !srcFile.toFile().isFile()) {
			LOGGER.log(Level.SEVERE, "File {0} does not exist.", srcFile);
			System.exit(0);
		}
	}

	public static void checkPathExist(Path path) {
		if (path.toFile().isDirectory() && path.toFile().exists()) {
			LOGGER.log(Level.SEVERE, "Path {0} does not exist.", path);
			System.exit(0);
		}
	}

	public static void checkSourceFileSize(Path srcFile) {
		if (srcFile.toFile().length() > Integer.MAX_VALUE) {
			LOGGER.log(Level.SEVERE, "Source file exceeds 2Gb.");
			System.exit(0);
		}
	}

	public String getFileExtension (Path srcFile) {
		return srcFile.toString().substring(srcFile.toString().lastIndexOf('.') + 1);
	}

	public Path setFileExtension(Path srcFile, String newExtension) {
		return Paths.get(srcFile.toString().substring(0, srcFile.toString().lastIndexOf('.') + 1) + newExtension);
	}
}
