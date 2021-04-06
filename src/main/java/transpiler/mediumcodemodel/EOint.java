package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOint extends EOData {

    private final long value;

    public EOint(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        parent.write(String.format("new %s(%dL)", org.eolang.EOint.class.getSimpleName(), this.value));
        return Optional.empty();
    }
}
