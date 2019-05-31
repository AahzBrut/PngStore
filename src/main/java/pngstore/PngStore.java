package pngstore;

import pngstore.commands.CommandExecutor;
import pngstore.commands.Operation;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PngStore {
	public static void main(String[] args) {
		if (args.length < 2) {
			CommandExecutor.execute(Operation.HELP, null, null);
		} else {
			Path srcFile = Paths.get(args[1]);
			Path dstFile = null;
			if (args.length > 2) dstFile = Paths.get(args[2]);
			CommandExecutor.execute(getCommand(args[0]), srcFile, dstFile);
		}
	}

	private static Operation getCommand(String cmd) {
		switch (cmd.toLowerCase()) {
			case "-p":
				return Operation.PACK;
			case "-u":
				return Operation.UNPACK;
			default:
				return Operation.HELP;
		}
	}
}
