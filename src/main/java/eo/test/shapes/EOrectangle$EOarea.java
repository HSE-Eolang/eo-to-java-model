package eo.test.shapes;

import eo.org.eolang.calc.EOmul;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;

/**
 * Площадь прямоугольника.
 */
public class EOrectangle$EOarea extends EOObject {

    @Override
    public EOData _getData() {

        EOData res = new EOmul(
                        _getAttribute("a"),
                        _getAttribute("b")
        )._setParent(this)._getData();
        return res;
    }
}
