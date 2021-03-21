package eo.org.eolang.calc;

import eo.org.eolang.core.data.EOData;
import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EOExpressionObject;
/**
 * Объект, при датаризации которого выполняется сравнение результатов датаризации свободных атрибутов.
 * val1 < val2
 */
public class EOless  extends EOExpressionObject {
    private EOObject val1;
    private EOObject val2;
    public EOless( EOObject val1, EOObject val2){
        this.val1 = val1._setParent(this);
        this.val2 = val2._setParent(this);
        calculate();
    }

    private void calculate() {
        if(val1._isCalculable() && val2._isCalculable()){
            if (val1._getData().isInteger() && val2._getData().isInteger()) {
                _data = new EOData(val1._getData().toInt() < val2._getData().toInt());
            }
            else if (val1._getData().isFloat() && val2._getData().isFloat()) {
                _data = new EOData(val1._getData().toFloat() < val2._getData().toFloat());
            }
        }
    }

    @Override
    public EOData _getData() {
        if(!_isCalculable()){
            calculate();
        }
        return _data;
    }
}
