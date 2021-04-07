package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public class EOfloat extends EOData {
    private final double value;

    public EOfloat(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        parentWriter.write(String.format("new %s(%fD)", org.eolang.EOfloat.class.getSimpleName(), this.value));
        return new ArrayList<>();
    }
}
