import org.eolang.EOstring;
import org.eolang.core.EOObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class MainMain {
    private final PrintStream stdout;

    /**
     * Ctor.
     * @param out The output
     */
    public MainMain(final PrintStream out) {
        this.stdout = out;
    }

    /**
     * The method caled by JVM when the program starts.
     * @param args Command line args
     * @throws Exception If fails
     */
    public static void main(final String... args) throws Exception {
        new MainMain(System.out).exec(args);
    }

    /**
     * The same method, but not static.
     * @param args Command line args
     * @throws Exception If fails
     */
    public void exec(final String... args) throws Exception {
        if (args.length == 0 || "--version".equals(args[0])) {
            this.version();
            return;
        }
        final String path = args[0].replaceAll("([^.]+)$", "EO$1");
        EOObject app = EOObject.class.cast(
                Class.forName(path).getConstructor().newInstance()
        );
        for (int i = 1; i < args.length; ++i) {
            // To Do
            final EOObject phi = new EOstring(args[i]);
            app = phi;
        }
        if (!app._getData().toBoolean()) {
            throw new IllegalStateException(
                    "Runtime dataization failure"
            );
        }
    }

    /**
     * Read the version from resources and prints it.
     * @throws IOException If fails
     */
    private void version() throws IOException {
        try (BufferedReader input =
                     new BufferedReader(
                             new InputStreamReader(//${project.version}
                                     Objects.requireNonNull(MainMain.class.getResourceAsStream("version.txt")),
                                     StandardCharsets.UTF_8)
                     )
        ) {
            this.stdout.printf(
                    "EOLANG Runtime v.%s",
                    input.lines().findFirst().get()
            );
        }
    }
}
