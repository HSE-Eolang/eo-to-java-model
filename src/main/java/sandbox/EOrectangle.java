package sandbox;

import org.eolang.*;
import org.eolang.core.*;

/** Package-scope object 'rectangle'. */
public class EOrectangle extends EOObject {

    /** Field for storing the 'a' free attribute. */
    private final EOObject EOa;
    /** Field for storing the 'b' free attribute. */
    private final EOObject EOb;

    /**
     * Constructs (via one-time-full application) the package-scope object 'rectangle'.
     *
     * @param EOa the object to bind to the 'a' free attribute.
     * @param EOb the object to bind to the 'b' free attribute.
     */
    public EOrectangle(EOObject EOa, EOObject EOb) {
        this.EOa = EOa;
        this.EOb = EOb;
    }

    /** Returns the object bound to the `a` input attribute. */
    public EOObject EOa() {
        return this.EOa;
    }

    /** Returns the object bound to the `b` input attribute. */
    public EOObject EOb() {
        return this.EOb;
    }

    /** Application-based bound attribute object 'area' */
    public EOObject EOarea() {
        return (this.EOa())._getAttribute("EOmul", this.EOb());
    }

    /** Abstraction-based bound attribute object 'perimeter' */
    public EOObject EOperimeter(EOObject EOz, EOObject EOq, EOObject EOh) {
        return new EOperimeter(EOz, EOq, EOh);
    }

    /** Attribute object 'perimeter' of the package-scope object 'rectangle'. */
    private class EOperimeter extends EOObject {

        /** Field for storing the 'z' free attribute. */
        private final EOObject EOz;
        /** Field for storing the 'q' free attribute. */
        private final EOObject EOq;
        /** Field for storing the 'h' free attribute. */
        private final EOObject EOh;

        /**
         * Constructs (via one-time-full application) the attribute object 'perimeter' of the
         * package-scope object 'rectangle'.
         *
         * @param EOz the object to bind to the 'z' free attribute.
         * @param EOq the object to bind to the 'q' free attribute.
         * @param EOh the object to bind to the 'h' free attribute.
         */
        public EOperimeter(EOObject EOz, EOObject EOq, EOObject EOh) {
            this.EOz = EOz;
            this.EOq = EOq;
            this.EOh = EOh;
        }

        /** Returns the parent object 'rectangle' of this object */
        @Override
        public EOObject _getParentObject() {
            return EOrectangle.this;
        }

        /** The decoratee of this object. */
        @Override
        public EOObject _getDecoratedObject() {
            return (this.EOa())
                    ._getAttribute(
                            "EOmul",
                            ((_getParentObject())._getAttribute("EOa"))
                                    ._getAttribute("EOadd", (_getParentObject())._getAttribute("EOb")));
        }

        /** Returns the object bound to the `z` input attribute. */
        public EOObject EOz() {
            return this.EOz;
        }

        /** Returns the object bound to the `q` input attribute. */
        public EOObject EOq() {
            return this.EOq;
        }

        /** Returns the object bound to the `h` input attribute. */
        public EOObject EOh() {
            return this.EOh;
        }

        /** Application-based bound attribute object 'a' */
        public EOObject EOa() {
            return new EOint(2L);
        }

        /** Abstraction-based bound attribute object 'halfPerimeterAbstr' */
        public EOObject EOhalfPerimeterAbstr() {
            return new EOhalfPerimeterAbstr();
        }

        /** Application-based bound attribute object 'halfPerimeterApplic' */
        public EOObject EOhalfPerimeterApplic() {
            return ((_getParentObject())._getAttribute("EOa"))
                    ._getAttribute("EOadd", (_getParentObject())._getAttribute("EOb"));
        }

        /**
         * Attribute object 'halfPerimeterAbstr' of the attribute object 'perimeter' of the
         * package-scope object 'rectangle'.
         */
        private class EOhalfPerimeterAbstr extends EOObject {

            /**
             * Constructs (via one-time-full application) the attribute object 'halfPerimeterAbstr' of the
             * attribute object 'perimeter' of the package-scope object 'rectangle'.
             */
            public EOhalfPerimeterAbstr() {}

            /** Returns the parent object 'perimeter' of this object */
            @Override
            public EOObject _getParentObject() {
                return EOperimeter.this;
            }

            /** The decoratee of this object. */
            @Override
            public EOObject _getDecoratedObject() {
                return new EOObject() {
                    /** The decoratee of this object. */
                    @Override
                    public EOObject _getDecoratedObject() {
                        return ((((_getParentObject())._getParentObject())._getParentObject())
                                ._getAttribute("EOa"))
                                ._getAttribute(
                                        "EOadd",
                                        (((_getParentObject())._getParentObject())._getParentObject())
                                                ._getAttribute("EOb"));
                    }
                };
            }
        }
    }
}
