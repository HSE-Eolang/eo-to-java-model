package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOfloat extends EOData {
    private final double value;

    public EOfloat(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        parent.write(String.format("new %s(%fD)", org.eolang.EOfloat.class.getSimpleName(), this.value));
        return Optional.empty();
    }
}
