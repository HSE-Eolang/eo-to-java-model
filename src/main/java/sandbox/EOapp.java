package sandbox;

import org.eolang.*;
import org.eolang.core.*;

/** Package-scope object 'app'. */
public class EOapp extends EOObject {

    /** Field for storing the 'args' variable-length free attribute. */
    private final EOarray EOargs;

    /**
     * Constructs (via one-time-full application) the package-scope object 'app'.
     *
     * @param EOargs the object to bind to the 'args' variable-length free attribute.
     */
    public EOapp(EOObject... EOargs) {
        this.EOargs = new EOarray(new EOObjectArray(EOargs));
    }

    /** The decoratee of this object. */
    @Override
    public EOObject _getDecoratedObject() {
        return new org.eolang.io.EOstdout(
                new org.eolang.txt.EOsprintf(
                        new EOstring(
                                "side a:%d\nside b:%d\narea: %d\nperimeter: %d\nhalfPerimAbst: %d\nhalfPerimApplic: %d\n"),
                        (this.EOrec1())._getAttribute("EOa"),
                        (this.EOrec1())._getAttribute("EOb"),
                        this.EOar(),
                        this.EOper(),
                        ((this.EOrec1())._getAttribute("EOperimeter"))._getAttribute("EOhalfPerimeterAbstr"),
                        (this.EOper())._getAttribute("EOhalfPerimeterApplic")));
    }

    /** Returns the object bound to the `args` input attribute. */
    public EOarray EOargs() {
        return this.EOargs;
    }

    /** Application-based bound attribute object 'rec1' */
    public EOObject EOrec1() {
        return new EOrectangle(
                new EOint(10L),
                new EOObject() {
                    /** The decoratee of this object. */
                    @Override
                    public EOObject _getDecoratedObject() {
                        return new EOint(15L);
                    }
                });
    }

    /** Application-based bound attribute object 'ar' */
    public EOObject EOar() {
        return (this.EOrec1())._getAttribute("EOarea");
    }

    /** Application-based bound attribute object 'per' */
    public EOObject EOper() {
        return (this.EOrec1())._getAttribute("EOperimeter");
    }
}
