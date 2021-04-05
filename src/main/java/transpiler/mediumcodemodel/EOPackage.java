package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOPackage extends EOSourceEntity {

    private final String packageName;
    private final ArrayList<EOSourceFile> files = new ArrayList<>();

    public EOPackage(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public ArrayList<EOSourceFile> getFiles() {
        return files;
    }

    public void addFile(EOSourceFile file) {
        files.add(file);
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        return null;
    }
}
