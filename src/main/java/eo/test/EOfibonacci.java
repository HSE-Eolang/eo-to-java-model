package eo.test;

import eo.org.eolang.calc.*;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.core.EOObject;

public class EOfibonacci extends EOObject{
    private EOObject n;

    public EOfibonacci(EOObject n){
        this.n = n._setParent(this);
    }

    @Override
    public EOData _getData() {
        EOData res = new EOif(
                new EOless(_getAttribute("n"), new EODataObject(3)),
                _getAttribute("small",
                        _getAttribute("n")
                ),
                _getAttribute("rec",
                        _getAttribute("n"),
                        new EODataObject(1L),
                        new EODataObject(1L)
                )
        )._setParent(this)._getData();
        //_freeAttributes();
        return res;
    }

}
