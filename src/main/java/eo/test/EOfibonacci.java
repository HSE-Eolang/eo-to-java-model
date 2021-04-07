package eo.test;

import org.eolang.calc.*;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;
import org.eolang.core.EOObject;

public class EOfibonacci extends EOObject{
    private EOObject n;

    public EOfibonacci(EOObject n){
        this.n = n._setParent(this);
    }

    @Override
    public EOData _getData() {
        EOData res = new EOif(
                new EOless(n, new EODataObject(3)),
                new EOfibonacci$EOsmall(n),
                new EOfibonacci$EOrec(
                        n,
                        new EODataObject(1L),
                        new EODataObject(1L)
                )
        )._setParent(this)._getData();
        return res;
    }

}
