package pngstore.commands;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class CommandExecutor {
	private static final Map<Operation, Command> allCommands = new EnumMap<>(Operation.class);

	static {
		allCommands.put(Operation.HELP, new PngHelpCommand());
		allCommands.put(Operation.PACK, new PngPackCommand());
		allCommands.put(Operation.UNPACK, new PngUnpackCommand());
	}

	private CommandExecutor() {}

	public static void execute(Operation operation, Path sourcePath, Path destinationPath) {
		allCommands.get(operation).execute(sourcePath, destinationPath);
	}
}
