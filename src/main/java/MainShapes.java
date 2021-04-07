import eo.test.shapes.EOrectangle;
import eo.test.shapes.EOtriangle;
import org.eolang.core.EOObject;
import org.eolang.core.data.EODataObject;
import org.eolang.io.EOstdout;
import org.eolang.txt.EOsprintf;

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
