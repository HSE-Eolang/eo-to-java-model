package transpiler.mediumcodemodel;

import transpiler.targetjavamodel.TargetJavaEntity;

public class EOInputAttribute extends EOSourceEntity {

    private boolean isVararg;
    private String name;

    public EOInputAttribute(String name, boolean isVararg) {
        this.name = name;
        this.isVararg = isVararg;
    }

    @Override
    public TargetJavaEntity transpile() {
        return null;
    }
}
