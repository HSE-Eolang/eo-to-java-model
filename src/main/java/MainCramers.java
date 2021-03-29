import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;
import org.eolang.EOfloat;

public class MainCramers {
    public static void main(String[] args) {
//        int arg = Integer.parseInt(args[0]);
        EOfloat ax1 = new EOfloat(5.0);
        EOfloat ax2 = new EOfloat(7.0);
        EOfloat a = new EOfloat(2.0);
        EOfloat bx1 = new EOfloat(1.0);
        EOfloat bx2 = new EOfloat(3.0);
        EOfloat b = new EOfloat(4.0);

        EOfloat diag1 = new EOfloat(ax1.EOmul(bx2)._getData().toFloat());
        EOfloat diag2 = new EOfloat(ax2.EOmul(bx1)._getData().toFloat());

        EOfloat delta1Diag1 = new EOfloat(a.EOmul(bx2)._getData().toFloat());
        EOfloat delta1Diag2 = new EOfloat(b.EOmul(ax2)._getData().toFloat());

        EOfloat delta2Diag1 = new EOfloat(ax1.EOmul(b)._getData().toFloat());
        EOfloat delta2Diag2 = new EOfloat(bx1.EOmul(a)._getData().toFloat());

        EOfloat delta = new EOfloat(diag1.EOsub(diag2)._getData().toFloat());
        EOfloat delta1 = new EOfloat(delta1Diag1.EOsub(delta1Diag2)._getData().toFloat());
        EOfloat delta2 = new EOfloat(delta2Diag1.EOsub(delta2Diag2)._getData().toFloat());
        EOfloat x1 = new EOfloat(delta1.EOdiv(delta)._getData().toFloat());
        EOfloat x2 = new EOfloat(delta2.EOdiv(delta)._getData().toFloat());
        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EOsprintf(
                                new EODataObject("Solution x1 %f and x2 is %f\n"),
                                x1,
                                x2

                        )
                )
        );
        stdout._getData();
    }
}
