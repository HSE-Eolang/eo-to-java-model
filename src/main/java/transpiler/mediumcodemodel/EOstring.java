package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public class EOstring extends EOData {
    private final String value;

    public EOstring(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        parentWriter.write(String.format("new %s(\"%s\")", org.eolang.EOstring.class.getSimpleName(), this.value));
        return new ArrayList<>();
    }
}
