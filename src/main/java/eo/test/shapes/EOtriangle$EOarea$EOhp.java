package eo.test.shapes;

import eo.org.eolang.calc.EOmul;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;

/**
 * Полупериметр.
 */
public class EOtriangle$EOarea$EOhp extends EOObject {

    public EOtriangle$EOarea$EOhp() {

    }

    @Override
    public EOData _getData() {
        EOObject per = new EODataObject(_getAttribute("perimeter")._getData());
        EOData res = new EOmul(
                        per,
                        new EODataObject(0.5)
        )._setParent(this)._getData();
        return res;
    }
}
