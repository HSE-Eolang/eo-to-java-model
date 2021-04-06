package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOchar extends EOData {
    private final char value;

    public EOchar(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        parent.write(String.format("new %s('%s')", org.eolang.EOchar.class.getSimpleName(), this.value));
        return Optional.empty();
    }
}
