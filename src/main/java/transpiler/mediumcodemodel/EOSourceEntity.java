package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;

public abstract class EOSourceEntity {

    public abstract ArrayList<EOTargetFile> transpile(PicoWriter parentWriter);
}
