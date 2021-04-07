package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;
import org.eolang.EOarray;
import org.eolang.core.EOObject;
import transpiler.medium2target.TranslationCommons;

import java.util.ArrayList;

public class EOInputAttribute extends EOSourceEntity {

    private final boolean isVararg;
    private final String name;
    private final String targetName;

    public EOInputAttribute(String name, boolean isVararg) {
        this.name = name;
        this.isVararg = isVararg;
        this.targetName = "EO" + name;
    }

    public boolean isVararg() {
        return isVararg;
    }

    public String getName() {
        return name;
    }

    public String getTargetName() {
        return targetName;
    }

    public String getDescription() {
        return String.format("'%s' %sfree attribute", this.name, this.isVararg ? "variable-length " : "");
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        TranslationCommons.bigComment(parentWriter, "Field for storing the " + getDescription() + ".");
        parentWriter.write("private final ");
        if (isVararg) {
            parentWriter.write(EOarray.class.getSimpleName());
        } else {
            parentWriter.write((EOObject.class.getSimpleName()));
        }
        parentWriter.writeln(String.format(" %s;", targetName));
        return new ArrayList<>();
    }
}
