import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;
import org.eolang.EOfloat;

public class MainCramers {
    public static void main(String[] args) {

//        cramer's rule for solving 2 equations
//        ax1 + ax2 = a;
//        bx1 + bx2 = b

//        equation 1
        EOfloat ax1 = new EOfloat(5.0);
        EOfloat ax2 = new EOfloat(7.0);
        EOfloat a = new EOfloat(2.0);

//        equation 2
        EOfloat bx1 = new EOfloat(1.0);
        EOfloat bx2 = new EOfloat(3.0);
        EOfloat b = new EOfloat(4.0);

        //matrix diagonals
        EOfloat deltaDiag1 = new EOfloat(
                ax1.EOmul(
                        bx2
                )._getData().toFloat()
        );
        EOfloat deltaDiag2 = new EOfloat(
                ax2.EOmul(
                        bx1
                )._getData().toFloat()
        );

        EOfloat delta1Diag1 = new EOfloat(
                a.EOmul(
                        bx2
                )._getData().toFloat()
        );
        EOfloat delta1Diag2 = new EOfloat(
                b.EOmul(
                        ax2
                )._getData().toFloat()
        );

        EOfloat delta2Diag1 = new EOfloat(
                ax1.EOmul(
                        b
                )._getData().toFloat()
        );
        EOfloat delta2Diag2 = new EOfloat(
                bx1.EOmul(
                        a
                )._getData().toFloat()
        );

//        determinants
        EOfloat delta = new EOfloat(
                deltaDiag1.EOsub(
                        deltaDiag2)._getData().toFloat()
        );
        EOfloat delta1 = new EOfloat(
                delta1Diag1.EOsub(
                        delta1Diag2)._getData().toFloat()
        );
        EOfloat delta2 = new EOfloat(
                delta2Diag1.EOsub(
                        delta2Diag2)._getData().toFloat()
        );

//        solutions
        EOfloat x1 = new EOfloat(
                delta1.EOdiv(
                        delta)._getData().toFloat()
        );
        EOfloat x2 = new EOfloat(
                delta2.EOdiv(
                        delta)._getData().toFloat()
        );

        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EOsprintf(
                                new EODataObject("Solution x1 is %f\nSolution x2 is %f\n"),
                                x1,
                                x2
                        )
                )
        );
        stdout._getData();
    }
}
