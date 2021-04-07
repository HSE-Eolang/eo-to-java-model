package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public class EOchar extends EOData {
    private final char value;

    public EOchar(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        parentWriter.write(String.format("new %s('%s')", org.eolang.EOchar.class.getSimpleName(), this.value));
        return new ArrayList<>();
    }
}
