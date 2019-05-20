package pngstore;

import pngstore.command.Command;
import pngstore.command.RestoreFromPng;
import pngstore.command.StoreToPng;

import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		Command command;
		if (args.length != 3)
			showHelp();
		else {
			switch (args[0]) {
				case "-store":
					command = new StoreToPng();
					command.execute(Paths.get(args[1]), Paths.get(args[2]));
					break;
				case "-restore":
					command = new RestoreFromPng();
					command.execute(Paths.get(args[1]), Paths.get(args[2]));
					break;
				default:
					showHelp();
					break;
			}
		}
	}

	private static void showHelp() {
		System.out.println("Usage:");
		System.out.println("\t pngstore -store <source file> <png file>");
		System.out.println("\t pngstore -restore <png file> <destination file>");
	}
}