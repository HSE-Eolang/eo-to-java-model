import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.io.EOstdout;
import eo.org.eolang.txt.EOsprintf;
import eo.test.EOfibonacci;

public class MainFibonacci {
    public static void main(String[] args) {
        int arg = Integer.parseInt(args[0]);
        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EODataObject("%dth Fibonacci number is %d\n"),
                        new EODataObject(arg),
                        new EOfibonacci(new EODataObject(arg))
                )
        );
        stdout._getData();
    }
}
