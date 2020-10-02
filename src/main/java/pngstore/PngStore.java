package pngstore;

import pngstore.commands.CommandExecutor;
import pngstore.commands.Operation;

import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import static pngstore.commands.Operation.*;

public class PngStore {

    private static final Map<String, Operation> operationDecoder = Map.ofEntries(
            new SimpleEntry<>("-p", PACK),
            new SimpleEntry<>("-n", UNPACK));

    public static void main(String[] args) {
        if (args.length < 2) {
            CommandExecutor.execute(HELP, null, null);
        } else {
            var srcFile = Paths.get(args[1]);
            var dstFile = args.length > 2 ? Paths.get(args[2]) : null;
            CommandExecutor.execute(getCommand(args[0]), srcFile, dstFile);
        }
    }

    private static Operation getCommand(String cmd) {

        return operationDecoder.getOrDefault(cmd, HELP);
    }
}
