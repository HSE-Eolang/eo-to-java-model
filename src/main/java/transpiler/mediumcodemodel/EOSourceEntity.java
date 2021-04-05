package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public abstract class EOSourceEntity {

    public abstract Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent);
}
