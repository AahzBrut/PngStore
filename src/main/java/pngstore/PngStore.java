package pngstore;

import pngstore.commands.CommandExecutor;
import pngstore.commands.Operation;

import java.nio.file.Paths;

import static pngstore.commands.Operation.HELP;

public class PngStore {

    public static void main(String[] args) {
        if (args.length < 2) {
            CommandExecutor.execute(HELP, null, null);
        } else {
            var srcFile = Paths.get(args[1]);
            var dstFile = args.length > 2 ? Paths.get(args[2]) : null;

            CommandExecutor.execute(
                    Operation.getOperationByCommandToken(args[0]),
                    srcFile,
                    dstFile);
        }
    }
}
