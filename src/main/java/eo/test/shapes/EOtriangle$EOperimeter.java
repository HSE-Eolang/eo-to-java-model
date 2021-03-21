package eo.test.shapes;

import eo.org.eolang.calc.EOadd;
import eo.org.eolang.calc.EOmul;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;

/**
 * Периметр треугольника.
 */
public class EOtriangle$EOperimeter extends EOObject {

    @Override
    public EOData _getData() {

        EOData res = new EOadd(
                        new EOadd(_getAttribute("a"),
                                _getAttribute("b")),
                        _getAttribute("c")
        )._setParent(this)._getData();
        return res;
    }
}
