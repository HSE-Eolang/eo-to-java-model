package eo.test;

import org.eolang.calc.EOadd;
import org.eolang.calc.EOequal;
import org.eolang.calc.EOif;
import org.eolang.calc.EOsub;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

public class EOfibonacci$EOrec extends EOObject {

    private EOObject n;
    private EOObject minus1;
    private EOObject minus2;

    public EOfibonacci$EOrec(EOObject n, EOObject minus1, EOObject minus2) {
        this.n = n._setParent(this);
        this.minus1 = minus1._setParent(this);
        this.minus2 = minus2._setParent(this);
    }

    @Override
    public EOData _getData() {
        EOData res = new EOif(
                new EOequal(n, new EODataObject(3)),
                new EOadd(minus1, minus2),
                new EOfibonacci$EOrec(
                        new EOsub(n, new EODataObject(1)),
                        new EOadd(minus1, minus2),
                        minus1
                )
        )._setParent(this)._getData();
        return res;
    }
}
