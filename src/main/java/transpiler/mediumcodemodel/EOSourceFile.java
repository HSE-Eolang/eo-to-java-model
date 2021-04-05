package transpiler.mediumcodemodel;

import org.ainslec.picocog.PicoWriter;

import java.util.ArrayList;
import java.util.Optional;

public class EOSourceFile extends EOSourceEntity {

    private final String fileName;
    private final EOPackage eoPackage;
    private ArrayList<EOAbstraction> objects;

    public EOSourceFile(String fileName, EOPackage eoPackage) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (eoPackage == null) {
            throw new IllegalArgumentException();
        }
        this.fileName = fileName;
        this.eoPackage = eoPackage;
    }

    public String getFileName() {
        return fileName;
    }

    public EOPackage getEoPackage() {
        return eoPackage;
    }

    public void setObjects(ArrayList<EOAbstraction> objects) {
        this.objects = objects;
    }

    @Override
    public Optional<ArrayList<EOTargetFile>> transpile(PicoWriter parent) {
        ArrayList<EOTargetFile> result = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            EOAbstraction abstraction = objects.get(i);
            result.addAll(abstraction.transpile(null).get());
        }

        return Optional.of(result);
    }
}
