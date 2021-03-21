package eo.test.shapes;

import eo.org.eolang.calc.EOadd;
import eo.org.eolang.calc.EOmul;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;

/**
 * Периметр прямоугольника.
 */
public class EOrectangle$EOperimeter extends EOObject {

    @Override
    public EOData _getData() {

        EOData res = new EOmul(
                        new EOadd(_getAttribute("a"),
                                _getAttribute("b")),
                        new EODataObject(2.0)
        )._setParent(this)._getData();
        return res;
    }
}
