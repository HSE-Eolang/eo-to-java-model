package eo.test;

import eo.org.eolang.calc.EOadd;
import eo.org.eolang.calc.EOequal;
import eo.org.eolang.calc.EOif;
import eo.org.eolang.calc.EOsub;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.core.EOObject;

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
                new EOequal(_getAttribute("n"), new EODataObject(3)),
                new EOadd(_getAttribute("minus1"), _getAttribute("minus2")),
                _getAttribute("rec",
                        new EOsub(_getAttribute("n"), new EODataObject(1)),
                        new EOadd(_getAttribute("minus1"), _getAttribute("minus2")),
                        _getAttribute("minus1")
                )
        )._setParent(this)._getData();
        return res;
    }
}
