package eo.test;

import eo.org.eolang.calc.EOadd;
import eo.org.eolang.calc.EOequal;
import eo.org.eolang.calc.EOif;
import eo.org.eolang.calc.EOsub;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.core.EOObject;

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
