package transpiler.mediumcodemodel;

import transpiler.targetjavamodel.TargetJavaEntity;

public class EOSourceFile extends EOSourceEntity {

    private final String fileName;
    private final EOPackage eoPackage;

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

    @Override
    public TargetJavaEntity transpile() {
        return null;
    }
}
