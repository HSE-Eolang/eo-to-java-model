import eo.org.eolang.core.EOObject;
import eo.org.eolang.core.data.EODataObject;
import eo.org.eolang.io.EOstdout;
import eo.org.eolang.txt.EOsprintf;
import eo.test.shapes.EOrectangle;
import eo.test.shapes.EOtriangle;

public class MainShapes {
    public static void main(String[] args) {
        EOObject rec = new EOrectangle(new EODataObject(5.3D), new EODataObject(7.4));
        EOObject rec2 = new EOtriangle(new EODataObject(5.0D), new EODataObject(5.0), new EODataObject(5.0D));
        EOObject stdout = new EOstdout(
                new EOsprintf(
                        new EODataObject("perimeter: %f area: %f\nperimeter: %f area: %f\n"),
                        rec._getAttribute("perimeter"),
                        rec._getAttribute("area"),
                        rec2._getAttribute("perimeter"),
                        rec2._getAttribute("area")
                )
        );
        stdout._getData();
    }
}
