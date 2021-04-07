package eo.test;

import org.eolang.calc.EOequal;
import org.eolang.calc.EOif;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

public class EOfibonacci$EOsmall extends EOObject {
    private EOObject n;

    public EOfibonacci$EOsmall(EOObject n) {
        this.n = n._setParent(this);
    }

    @Override
    public EOData _getData() {
        EOData res = new EOif(
                new EOequal(
                        _getAttribute("n"),
                        new EODataObject(2)
                ),
                new EODataObject(1L),
                _getAttribute("n")
        )._setParent(this)._getData();
        //_freeAttributes();
        return res;
    }
}
