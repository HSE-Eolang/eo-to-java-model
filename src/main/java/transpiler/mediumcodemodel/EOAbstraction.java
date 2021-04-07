package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;
import org.eolang.EOarray;
import org.eolang.core.EOObject;
import org.eolang.core.EOObjectArray;
import transpiler.medium2target.TranslationCommons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class EOAbstraction extends EOSourceEntity {
    private final Optional<String> instanceName;

    private final ArrayList<EOInputAttribute> freeAttributes;
    private final String xmlName;
    private final ArrayList<EOAbstraction> subAbstractions;
    private final Optional<String> targetName;
    private ArrayList<EOApplication> boundAttributes;
    private EOSourceEntity scope;

    public EOAbstraction(String xmlName, Optional<String> instanceName, ArrayList<EOInputAttribute> freeAttributes) {
        this.xmlName = xmlName;
        this.instanceName = instanceName;
        this.freeAttributes = freeAttributes;
        this.subAbstractions = new ArrayList<>();
        if (instanceName.isPresent()) {
            targetName = Optional.of("EO" + instanceName.get());
        } else {
            targetName = Optional.empty();
        }
    }

    public void setApplications(ArrayList<EOApplication> applications) {
        this.boundAttributes = applications;
    }

    public void addSubAbstraction(EOAbstraction abstraction) {
        this.subAbstractions.add(abstraction);
    }

    public Optional<String> getInstanceName() {
        return instanceName;
    }

    public ArrayList<EOInputAttribute> getFreeAttributes() {
        return freeAttributes;
    }

    public String getXmlName() {
        return xmlName;
    }

    public ArrayList<EOApplication> getBoundAttributes() {
        return boundAttributes;
    }

    public ArrayList<EOAbstraction> getSubAbstractions() {
        return subAbstractions;
    }

    public EOSourceEntity getScope() {
        return scope;
    }

    public void setScope(EOSourceEntity scope) {
        this.scope = scope;
    }

    private String getScopeType() {
        if (scope instanceof EOSourceFile) {
            return "package";
        } else if (scope instanceof EOAbstraction) {
            if (!instanceName.isPresent()) {
                return "anonymous";
            } else {
                return "attribute";
            }
        }
        return null;
    }

    public Optional<String> getTargetName() {
        return targetName;
    }

    private String getNestedChain() {
        EOAbstraction scope = (EOAbstraction) getScope();
        String format = String.format("attribute object '%s' \nof the", this.instanceName.get());
        while (true) {
            if (scope.getScopeType().equals("attribute")) {
                format = format + String.format(" attribute object '%s' \nof the", scope.instanceName.get());
                scope = (EOAbstraction) scope.getScope();
            } else {
                format = format + String.format(" package-scope object '%s'", scope.instanceName.get());
                break;
            }
        }
        return format;
    }

    public String getArgsString() {
        String result = "";
        for (int i = 0; i < freeAttributes.size(); i++) {
            EOInputAttribute attr = freeAttributes.get(i);
            String type = EOObject.class.getSimpleName();
            if (attr.isVararg()) {
                type = type + "...";
            }
            result += String.format("%s %s", type, attr.getTargetName());
            if (i != freeAttributes.size() - 1) {
                result += ", ";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (instanceName.isPresent()) {
            return "abstraction " + instanceName.get();
        } else {
            return "anonymous abstraction";
        }
    }

    @Override
    public ArrayList<EOTargetFile> transpile(PicoWriter parentWriter) {
        if (getScopeType().equals("package")) {
            EOSourceFile file = ((EOSourceFile) scope);
            PicoWriter w = new PicoWriter();

            transpileFileHeader(w, file);
            transpileClass(w);

            ArrayList<EOTargetFile> result = new ArrayList<>();
            result.add(new EOTargetFile(String.format("%s.java", this.targetName.get()), w.toString()));
            return result;
        } else if (getScopeType().equals("attribute")) {
            transpileClass(parentWriter);
            return new ArrayList<>();
        } else {
            transpileClassAnonymous(parentWriter);
            return new ArrayList<>();
        }
    }

    private void transpileFileHeader(PicoWriter w, EOSourceFile file) {
        /* package statement */
        w.writeln(String.format("package %s;", file.getEoPackage().getPackageName()));
        w.writeln("");
        /* import the base language objects */
        w.writeln(String.format("import org.eolang.*;"));
        w.writeln(String.format("import org.eolang.core.*;"));
        w.writeln("");
    }

    private void transpileClass(PicoWriter w) {
        if (getScopeType().equals("package")) {
            TranslationCommons.bigComment(w, String.format("Package-scope object '%s'.", this.instanceName.get()));
            w.writeln_r(String.format("public class %s extends %s {", this.targetName.get(), EOObject.class.getSimpleName()));
        } else {
            TranslationCommons.bigComment(w, (getNestedChain() + ".").replaceFirst("a", "A").split("\n"));
            w.writeln_r(String.format("private class %s extends %s {", this.targetName.get(), EOObject.class.getSimpleName()));

        }
        transpileClassContents(w);
        w.writeln_l("}");
        w.writeln("");
    }

    private void transpileClassAnonymous(PicoWriter w) {
        w.writeln_r(String.format("new %s() {", EOObject.class.getSimpleName()));
        transpileClassContents(w);
        w.writeln_l("}");
        w.writeln("");
    }

    private void transpileClassContents(PicoWriter w) {
        if (freeAttributes.size() > 0) {
            w.writeln("");
            transpileFreeAttributes(w);
        }
        w.writeln("");
        if (getTargetName().isPresent()) {
            transpileConstructor(w);
            w.writeln("");
        }

        if (getScopeType().equals("attribute")) {
            transpileParent(w);
        }
        w.writeln("");

        if (boundAttributes.size() > 0) {
            transpileApplications(w);
        }

        if (subAbstractions.size() > 0) {
            transpileSubabstractions(w);
        }
    }

    private void transpileParent(PicoWriter w) {
        EOAbstraction scope = (EOAbstraction) getScope();
        w.writeln("@Override");
        w.writeln_r(String.format("public %s _getParentObject() {", EOObject.class.getSimpleName()));
        w.writeln(String.format("return %s.this;", scope.targetName.get()));
        w.writeln_l("}");
    }

    private void transpileFreeAttributes(PicoWriter w) {
        for (EOInputAttribute attr : freeAttributes) {
            attr.transpile(w);
        }
    }

    private void transpileConstructor(PicoWriter w) {
        ArrayList<String> commentParams = new ArrayList<>();
        if (getScopeType().equals("package")) {
            commentParams.add(String.format("Constructs (via one-time-full application) the package-scope object '%s'.", this.instanceName.get()));
        } else {
            commentParams.addAll(Arrays.asList(String.format("Constructs (via one-time-full application) the %s.", getNestedChain()).split("\n")));
        }
        if (freeAttributes.size() > 0) {
            for (int i = 0; i < freeAttributes.size(); i++) {
                EOInputAttribute attr = freeAttributes.get(i);
                commentParams.add(String.format("@param %s the object to bind to the %s.", attr.getTargetName(), attr.getDescription()));
            }
        }
        TranslationCommons.bigComment(w, commentParams.toArray(String[]::new));
        w.write(String.format("public %s(", this.targetName.get()));
        if (freeAttributes.size() == 0) {
            w.writeln(") {}");
        } else {
            w.write(getArgsString());
            w.writeln_r(") {");
            for (int i = 0; i < freeAttributes.size(); i++) {
                EOInputAttribute attr = freeAttributes.get(i);
                String wrapper;
                if (attr.isVararg()) {
                    wrapper = String.format("new %s(new %s(%s))", EOarray.class.getSimpleName(), EOObjectArray.class.getSimpleName(), attr.getTargetName());
                } else {
                    wrapper = attr.getTargetName();
                }
                w.writeln(String.format("this.%s = %s;", attr.getTargetName(), wrapper));
            }
            w.writeln_l("}");
        }
    }


    private void transpileSubabstractions(PicoWriter w) {
        for (int i = 0; i < subAbstractions.size(); i++) {
            subAbstractions.get(i).transpile(w);
            w.writeln("");
        }
    }

    private void transpileApplications(PicoWriter w) {
        for (int i = 0; i < boundAttributes.size(); i++) {
            boundAttributes.get(i).transpile(w);
            w.writeln("");
        }
    }
}