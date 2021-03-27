package org.eolang;

import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;

/***
 * Represents an integer number
 * @version %I%, %G%
 */
public class EOint extends EOObject {

    private final long value;

    public EOint(long value) {
        this.value = value;
    }

    @Override
    public EOData _getData() {
        return new EOData(value);
    }

    /***
     * Sums this integer and the {@code rightAddend} free attribute
     * @param rightAddend a number to be added to this integer
     * @return An object representing a sum of this integer and the {@code rightAddend} free attribute
     */
    public EOint add(EOObject rightAddend) {
        return new EOint(this.value + rightAddend._getData().toInt());
    }

    /***
     * Subtracts the {@code subtrahend} free attribute from this integer
     * @param subtrahend a number to be subtracted from this integer
     * @return An object representing a difference of this integer and the {@code subtrahend} free attribute
     */
    public EOint sub(EOObject subtrahend) {
        return new EOint(this.value - subtrahend._getData().toInt());
    }

    public EOint div(EOObject divisor) {
        // TODO add check if divisor == 0 then return exception object
        return new EOint(Math.floorDiv(this.value, divisor._getData().toInt()));
    }

    public EOint mul(EOObject rightFactor) {
        return new EOint(this.value * rightFactor._getData().toInt());
    }

}
