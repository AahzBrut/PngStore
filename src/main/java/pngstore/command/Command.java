package pngstore.command;

import java.nio.file.Path;

public interface Command {
	void execute(Path source, Path destination);
}