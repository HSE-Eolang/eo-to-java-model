package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EObool extends EOData {
    private final boolean value;

    public EObool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        parent.write(String.format("new %s(%b)", org.eolang.EObool.class.getSimpleName(), this.value));
        return Optional.empty();
    }
}
