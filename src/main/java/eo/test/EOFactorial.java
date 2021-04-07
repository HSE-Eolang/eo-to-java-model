package eo.test;

import org.eolang.calc.EOif;
import org.eolang.calc.EOless;
import org.eolang.calc.EOmul;
import org.eolang.calc.EOsub;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

public class EOFactorial extends EOObject {
    private EOObject n;

    public EOFactorial(EOObject n) {
        this.n = n._setParent(this);
    }

    @Override
    public EOData _getData() {
        EOData res = new EOif(
                new EOless(_getAttribute("n"), new EODataObject(2)),
                new EODataObject(1),
                new EOmul(_getAttribute("n"), new EOFactorial(new EOsub(_getAttribute("n"), new EODataObject(1))))
        )._setParent(this)._getData();
        //_freeAttributes();
        return res;
    }
}
