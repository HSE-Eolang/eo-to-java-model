package org.eolang;

import org.eolang.core.EOObject;
import org.eolang.core.data.EOData;

/***
 * Represents a character type
 * @version %I%, %G%
 */
public class EOchar extends EOObject {
    private char character;

    public EOchar(char character){
        this.character = character;
    }

    @Override
    public EOData _getData() {
        return new EOData(character);
    }

    /***
     * Converts this character to a string
     * @return An object representing the string type of this character
     */
    public EOstring EOtoString() {
        return new EOstring(Character.toString(character));
    }
}
