package eo.test.newModel;

import org.eolang.EOarray;
import org.eolang.EOint;
import org.eolang.EOstring;
import org.eolang.core.EOObject;
import org.eolang.core.EOObjectArray;
import org.eolang.core.data.EOData;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;

/***
 * Package-scope object 'app'
 */
public class EOapp extends EOObject {

    /***
     * Free variable-length attribute 'args'
     */
    private final EOarray args;

    /***
     * Constructs (via full-one-time-application) the package-scope object 'app'
     * @param args free variable-length attribute 'args'
     */
    public EOapp(EOObject... args) {
        this.args = new EOarray(new EOObjectArray(args));
    }

    /***
     * Input variable-length attribute 'args'
     * @return object bound to the input variable-length attribute 'args'
     */
    public EOObject EOargs() {
        return this.args;
    }

    /***
     * Output attribute 'rec1'
     * @return object bound to the output attribute 'rec1'
     */
    public EOObject EOrec1() {
        return new EOrectangle
                (
                        new EOint(10L),
                        /***
                         * Anonymous local object passed as an argument to be bound to the free attribute 'b' of a rectangle object
                         */
                        new EOObject() {
                            @Override
                            public EOData _getData() {
                                return new EOint(15L)._getData();
                            }
                        }
                );
    }

    /***
     * Output float-up attribute 'ar'
     * @return object bound to the output float-up attribute 'ar'
     */
    public EOObject EOar() {
        return EOrec1()._getAttribute("area");
    }

    /***
     * Output float-up attribute 'per'
     * @return object bound to the output float-up attribute 'per'
     */
    public EOObject EOper() {
        return EOrec1()._getAttribute("perimeter");
    }

    @Override
    public EOData _getData() {
        return new EOstdout(
                new EOsprintf(
                        new EOstring("side a:%s\nside b:%s\narea: %s\nperimeter: %s\nhalfPerimAbst: %s\nhalfPerimApplic: %s\n"),
                        EOrec1()._getAttribute("a"),
                        EOrec1()._getAttribute("b"),
                        EOar(),
                        EOper(),
                        EOrec1()._getAttribute("perimeter")._getAttribute("halfPerimeterAbstr"),
                        EOper()._getAttribute("halfPerimeterApplic")
                )
        )._getData();
    }
}
