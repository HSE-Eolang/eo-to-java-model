package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public class EOint extends EOData {

    private final long value;

    public EOint(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        parentWriter.write(String.format("new %s(%dL)", org.eolang.EOint.class.getSimpleName(), this.value));
        return new ArrayList<>();
    }
}
