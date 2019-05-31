package pngstore.commands;

import java.nio.file.Path;

interface Command {
	void execute(Path sourcePath, Path destinatioPath);
}
