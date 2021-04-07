import eo.test.EOCramers;
import org.eolang.EOfloat;
import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;

public class MainCramers {
    public static void main(String[] args) {
//        cramer's rule for solving 2 equations
//        ax1 + ax2 = a;
//        bx1 + bx2 = b

//        Example 1
//        equation 1
        EOfloat ax1 = new EOfloat(5.0);
        EOfloat ax2 = new EOfloat(7.0);
        EOfloat a = new EOfloat(2.0);
//        equation 2
        EOfloat bx1 = new EOfloat(7.0);
        EOfloat bx2 = new EOfloat(3.0);
        EOfloat b = new EOfloat(5.0);

        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EODataObject(
                                "Example #1\n %s\n" +
                                        "Example #2\n %s\n" +
                                        "Example #3\n %s\n"),
                        new EOCramers(ax1, ax2, a, bx1, bx2, b),
                        new EOCramers(
                                new EOfloat(3.0),
                                new EOfloat(9.0),
                                new EOfloat(4.0),
                                new EOfloat(6.0),
                                new EOfloat(2.0),
                                new EOfloat(1.0)
                        ),
                        new EOCramers(
                                new EOfloat(3.0),
                                new EOfloat(2.0),
                                new EOfloat(1.0),
                                new EOfloat(8.0),
                                new EOfloat(4.0),
                                new EOfloat(2.0)
                        )

                )
        );
        stdout._getData();

    }
}
