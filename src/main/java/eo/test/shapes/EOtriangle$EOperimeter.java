package eo.test.shapes;

import org.eolang.calc.EOadd;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;

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
