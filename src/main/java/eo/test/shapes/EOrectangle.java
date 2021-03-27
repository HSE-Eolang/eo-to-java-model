package eo.test.shapes;

import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

/**
 * Прямоугольник.
 */
public class EOrectangle extends EOObject {
    private EOObject a;
    private EOObject b;

    public EOrectangle(EOObject a, EOObject b) {
        this.a = a._setParent(this);
        this.b = b._setParent(this);
    }

    @Override
    public EOData _getData() {
        return new EODataObject(2)._setParent(this)._getData();
    }
}
