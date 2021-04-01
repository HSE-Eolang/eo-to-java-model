package eo.test.newModel;

import org.eolang.EOint;
import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;

/***
 * Package-scope object 'rectangle'
 */
public class EOrectangle extends EOObject {

    /***
     * Free attribute 'a'
     */
    private final EOObject EOa;
    /***
     * Free attribute 'b'
     */
    private final EOObject EOb;

    /***
     * Constructs (via full-one-time-application) the package-scope object 'rectangle'
     * @param EOa free attribute 'a'
     * @param EOb free attribute 'b'
     */
    public EOrectangle(EOObject EOa, EOObject EOb) {
        this.EOa = EOa;
        this.EOb = EOb;
    }

    /***
     * Input attribute 'a'
     * @return object bound to the input attribute 'a'
     */
    public EOObject EOa() {
        return this.EOa;
    }

    /***
     * Input attribute 'b'
     * @return object bound to the input attribute 'b'
     */
    public EOObject EOb() {
        return this.EOb;
    }

    /***
     * Output attribute 'area'
     * @return object bound to the output attribute 'area'
     */
    public EOObject EOarea() {
        return EOa()._getAttribute("mul", EOb());
    }

    /***
     * Output attribute 'perimeter'
     * @return object bound to the output attribute 'perimeter'
     */
    public EOObject EOperimeter() {
        return new EOperimeter();
    }

    /***
     * Attribute object 'perimeter'
     * of the package-scope object 'rectangle'
     */
    private class EOperimeter extends EOObject {

        /***
         * Output attribute 'a'
         * @return object bound to the output attribute 'a'
         */
        public EOObject EOa() {
            return new EOint(2L);
        }

        /***
         * Output attribute 'halfPerimeterAbstr'
         * @return object bound to the output attribute 'halfPerimeterAbstr'
         */
        public EOObject EOhalfPerimeterAbstr() {
            return new EOhalfPerimeterAbstr();
        }

        /***
         * Output attribute 'halfPerimeterApplic'
         * @return object bound to the output attribute 'halfPerimeterApplic'
         */
        public EOObject EOhalfPerimeterApplic() {
            return EOrectangle.this.EOa()
                    ._getAttribute("add", EOrectangle.this.EOb());
        }

        @Override
        public EOData _getData() {
            return EOa()._getAttribute("mul", EOrectangle.this.EOa()._getAttribute("add", EOrectangle.this.EOb()))._getData();
        }

        /***
         * Attribute object 'halfPerimeterAbstr'
         * of the attribute object 'perimeter'
         * of the package-scope object 'rectangle'
         */
        private class EOhalfPerimeterAbstr extends EOObject {

            @Override
            public EOData _getData() {
                /***
                 * Anonymous local-scope object bound to the '@' attribute
                 * of the attribute object 'halfPerimeterAbstr'
                 * of the attribute object 'perimeter'
                 * of the package-scope object 'rectangle'
                 */
                return new EOObject() {
                    @Override
                    public EOData _getData() {
                        return EOrectangle.this.EOa()._getAttribute("add", EOrectangle.this.EOb())._getData();
                    }
                }._getData();
            }
        }
    }
}
