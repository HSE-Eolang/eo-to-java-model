package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOstring extends EOData {
    private final String value;

    public EOstring(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        parent.write(String.format("new %s(\"%s\")", org.eolang.EOstring.class.getSimpleName(), this.value));
        return Optional.empty();
    }
}
