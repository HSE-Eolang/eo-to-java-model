import eo.test.EOfibonacci;
import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;

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
