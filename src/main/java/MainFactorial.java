import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;
import eo.test.EOFactorial;

public class MainFactorial {
    public static void main(String[] args) {
        int arg = Integer.parseInt(args[0]);
        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EOsprintf(
                                new EODataObject("Factorial of %d  is %d\n"),
                                new EODataObject(arg),
                                new EOFactorial(new EODataObject(arg))
                        )
                )
        );
        stdout._getData();
    }
}
