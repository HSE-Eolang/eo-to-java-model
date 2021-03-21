package eo.org.eolang.core.data;

import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOData;

import java.lang.reflect.Field;

/**
 * Объект хранящий данные, используется для работы с Data Objects из EO.
 */
public class EODataObject extends EOObject {

    /**
     * Хранимые данные.
     */
    protected EOData _data;

    public boolean _isCalculable(){return true;}

    public EODataObject(Object _data) {
        this._data = new EOData(_data);
    }

    public EODataObject() {
        this._data = new EONoData();
    }

    public EODataObject(EOData _data) {
        this._data = _data;
    }

    /**
     * Датаризация объекта.
     */
    public EOData _getData() {
        return _data;
    }

    public boolean isNoData(){
        return _data.getClass().equals(EONoData.class);
    }

    public EOObject _clone() {
        try{
            EOObject res  = (EOObject)this.clone();
            return res;
        }catch (CloneNotSupportedException cnsException){
            cnsException.printStackTrace();
        }
        return new EODataObject();
    }
}
