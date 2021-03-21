package eo.test.shapes;

import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;

/**
 * Треугольник.
 */
public class EOtriangle extends EOObject {
    private EOObject a;
    private EOObject b;
    private EOObject c;

    public EOtriangle(EOObject a, EOObject b, EOObject c) {
        this.a = a._setParent(this);
        this.b = b._setParent(this);
        this.c = c._setParent(this);
    }

    @Override
    public EOData _getData() {
        return new EODataObject(1)._setParent(this)._getData();
    }
}
