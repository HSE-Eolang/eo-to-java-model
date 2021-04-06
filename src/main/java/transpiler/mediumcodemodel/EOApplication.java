package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;
import org.eolang.core.EOObject;

import java.util.ArrayList;
import java.util.Optional;

public class EOApplication extends EOSourceEntity {

    private final boolean isDotNotation;
    private final String appliedObject;
    private final Optional<String> name;
    private final Optional<String> targetName;
    private EOSourceEntity scope;
    private EOApplication dotNotationBase;
    private EOAbstraction wrappedAbstraction;
    private ArrayList<EOApplication> arguments;
    private Optional<EOData> data = Optional.empty();

    public EOApplication(boolean isDotNotation, String appliedObject, Optional<String> name, Optional<EOData> data) {
        this.isDotNotation = isDotNotation;
        this.dotNotationBase = dotNotationBase;
        this.appliedObject = appliedObject;
        this.arguments = arguments;
        this.name = name;
        if (name.isPresent()) {
            targetName = Optional.of("EO" + name.get());
        } else {
            targetName = Optional.empty();
        }
        this.data = data;
    }

    public EOAbstraction getWrappedAbstraction() {
        return wrappedAbstraction;
    }

    public void setWrappedAbstraction(EOAbstraction wrappedAbstraction) {
        if (this.wrappedAbstraction != null) {
            throw new RuntimeException("application already has the wrapped abstraction");
        }
        this.wrappedAbstraction = wrappedAbstraction;
    }

    public EOSourceEntity getScope() {
        return scope;
    }

    public void setScope(EOSourceEntity scope) {
        this.scope = scope;
    }

    public boolean isDotNotation() {
        return isDotNotation;
    }

    public EOApplication getDotNotationBase() {
        return dotNotationBase;
    }

    public void setDotNotationBase(EOApplication dotNotationBase) {
        this.dotNotationBase = dotNotationBase;
    }

    public String getAppliedObject() {
        return appliedObject;
    }

    public ArrayList<EOApplication> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<EOApplication> arguments) {
        this.arguments = arguments;
    }

    public Optional<String> getName() {
        return name;
    }

    private EOApplication isAccessible(String base) {
        EOAbstraction scope = (EOAbstraction) getScope();
        return scope.getBoundAttributes().stream().filter(a -> a.targetName.equals(base)).findAny().orElse(null);
    }

    private String aliasToNormalForm() {
        String[] components = appliedObject.split("\\.");
        components[components.length - 1] = "EO" + components[components.length - 1];
        String result = "";
        for (int i = 0; i < components.length; i++) {
            result += components[i];
            if (i != components.length - 1) {
                result += ".";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (name.isPresent()) {
            return "application " + name.get();
        } else {
            return "anonymous application of the object " + appliedObject;
        }
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter w) {
        // unnamed applications (i.e. nested)
        if (!targetName.isPresent()) {
            transpileApplication(w);
            return Optional.empty();
        }
        // decoratees
        if (name.get().equals("@")) {
            w.writeln("@Override");
            w.writeln_r(String.format("public %s _getDecoratedObject() {", EOObject.class.getSimpleName(), targetName.get()));
            w.write("return ");
            transpileApplication(w);
            w.write(";");
            w.writeln_l("}");
            return Optional.empty();
        }
        // wrappers for abstraction
        if (wrappedAbstraction != null) {
            w.writeln_r(String.format("public %s %s(%s) {", EOObject.class.getSimpleName(), targetName.get(), wrappedAbstraction.getArgsString()));
            // wrapper for anonymous abstraction
            if (!wrappedAbstraction.getTargetName().isPresent()) {
                w.write("return ");
                wrappedAbstraction.transpile(w);
                w.write(";");
            }
            // wrapper for named abstraction
            else {
                w.write(String.format("return new %s(", wrappedAbstraction.getTargetName().get()));
                for (int i = 0; i < wrappedAbstraction.getFreeAttributes().size(); i++) {
                    EOInputAttribute inp = wrappedAbstraction.getFreeAttributes().get(i);
                    w.write(inp.getTargetName());
                    if (i != wrappedAbstraction.getFreeAttributes().size() - 1) {
                        w.write(", ");
                    }
                }
                w.write(");");
            }
            w.writeln_l("}");
            return Optional.empty();
        }
        // plain application-based bound attribute
        w.writeln_r(String.format("public %s %s() {", EOObject.class.getSimpleName(), targetName.get()));
        w.write("return ");
        transpileApplication(w);
        w.write(";");
        w.writeln_l("}");
        return Optional.empty();
    }

    private void transpileApplication(PicoWriter w) {
        // data stored inside application
        if (data.isPresent()) {
            data.get().transpile(w);
            return;
        }
        // dot-notation based application
        if (isDotNotation) {
            w.write("(");
            getDotNotationBase().transpileApplication(w);
            w.write(")._getAttribute(");
            w.write(appliedObject);
            if (arguments.size() > 0) {
                w.write(", ");
                transpileArgs(w);
            }
            w.write(")");
            return;
        }
        // non-nested plain application
        if (isAccessible(appliedObject) != null) {
            // that cannot be referenced
            w.writeln(appliedObject);
            w.write("(");
            if (arguments.size() > 0) {
                transpileArgs(w);
            }
            w.write(")");
        } else {
            if(appliedObject.equals("^")) {
                w.write("_getParentObject()");
                return;
            }
            else {
                w.write("new ");
                w.write(aliasToNormalForm());
                w.write("(");
                if (arguments.size() > 0) {
                    transpileArgs(w);
                }
                w.write(")");
            }
        }
    }

    private void transpileArgs(PicoWriter w) {
        for (int i = 0; i < arguments.size(); i++) {
            EOApplication arg = arguments.get(i);
            if (arg.wrappedAbstraction != null) {
                if (!arg.wrappedAbstraction.getInstanceName().isPresent()) {
                    arg.wrappedAbstraction.transpile(w);
                    return;
                }
            }
            arg.transpileApplication(w);
            if (i != arguments.size() - 1){
                w.write(", ");
            }
        }
    }

    private Optional<ArrayList<EOTargetFile>> transpileDecoration(PicoWriter w) {
        return Optional.empty();
    }
}
