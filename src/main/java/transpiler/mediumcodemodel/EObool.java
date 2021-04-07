package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public class EObool extends EOData {
    private final boolean value;

    public EObool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        parentWriter.write(String.format("new %s(%b)", org.eolang.EObool.class.getSimpleName(), this.value));
        return new ArrayList<>();
    }
}
