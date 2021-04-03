package transpiler.mediumcodemodel;

import transpiler.targetjavamodel.TargetJavaEntity;

import java.util.ArrayList;
import java.util.Optional;

public class EOApplication extends EOSourceEntity {

    private EOSourceEntity scope;

    private boolean isDotNotation;

    private EOApplication dotNotationBase;

    private String appliedObject;

    private ArrayList<EOApplication> arguments;

    private Optional<String> name;

    public EOApplication(boolean isDotNotation, String appliedObject, Optional<String> name) {
        this.isDotNotation = isDotNotation;
        this.dotNotationBase = dotNotationBase;
        this.appliedObject = appliedObject;
        this.arguments = arguments;
        this.name = name;
    }

    public void setScope(EOSourceEntity scope) {
        this.scope = scope;
    }

    public void setDotNotationBase(EOApplication dotNotationBase) {
        this.dotNotationBase = dotNotationBase;
    }

    public void setArguments(ArrayList<EOApplication> arguments) {
        this.arguments = arguments;
    }

    @Override
    public TargetJavaEntity transpile() {
        return null;
    }
}
