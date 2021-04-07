package eo.test;

import org.eolang.EOfloat;
import org.eolang.EOstring;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;

public class EOCramers extends EOObject {
    //        equation 1
    EOfloat ax1;
    EOfloat ax2;
    EOfloat a;

    //        equation 2
    EOfloat bx1;
    EOfloat bx2;
    EOfloat b;

    public EOCramers(EOfloat ax1, EOfloat ax2, EOfloat a,EOfloat bx1, EOfloat bx2, EOfloat b){
        this.ax1 = ax1;
        this.ax2 = ax2;
        this.a = a;
        this.bx1 = bx1;
        this.bx2 = bx2;
        this.b = b;
    }

    @Override
    public EOData _getData() {
        //        cramer's rule for solving 2 equations
//        ax1 + ax2 = a;
//        bx1 + bx2 = b


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
        EOstring solution = new EOstring("Solution x1: " + x1._getData().toString() + "\nSolution x2: " + x2._getData().toString());
        return solution._getData();
    }
}
