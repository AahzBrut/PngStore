package pngstore;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

    private FileUtil() {
    }

    public static void checkFileExist(Path srcFile) {
        if (!srcFile.toFile().exists() || !srcFile.toFile().isFile()) {
            LOGGER.log(Level.SEVERE, "File {0} does not exist.", srcFile);
            System.exit(0);
        }
    }
}
