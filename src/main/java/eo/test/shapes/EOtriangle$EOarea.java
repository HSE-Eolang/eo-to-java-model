package eo.test.shapes;

import org.eolang.calc.EOmul;
import org.eolang.calc.EOpow;
import org.eolang.calc.EOsub;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

/**
 * Площадь треугольника.
 */
public class EOtriangle$EOarea extends EOObject {

    @Override
    public EOData _getData() {
        EOObject h = _getAttribute("hp");
        EODataObject hp = new EODataObject(h._getData());
        EODataObject a = new EODataObject(new EOsub(
                hp,
                _getAttribute("a")
        )._getData());
        EOObject b = new EOsub(
                hp,
                _getAttribute("b")
        );
        EOObject c = new EOsub(
                hp,
                _getAttribute("c")
        );
        EOData res = new EOpow(
                new EOmul(
                        hp,
                        new EOmul(
                                a,
                                new EOmul(b, c)
                        )
                ),
                new EODataObject(0.5)
        )._setParent(this)._getData();
        return res;
    }
}
