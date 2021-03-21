package eo.org.eolang.core;

import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EONoData;

import java.util.Arrays;

public class EOObjectArray extends EOObject{
    protected EOObject[] _array;

    public EOObjectArray(){
        _array = new EOObject[0];
    }

    public EOObjectArray(EOObject... objects){
        _array = objects;
    }

    @Override
    public EOData _getData(){
        return Arrays.stream(_array)
                .map(eoObject -> eoObject._getData())
                .skip(_array.length - 1).findFirst()
                .orElse(new EONoData());
    }
}
