package transpiler.mediumcodemodel;

public class EOTargetFile {
    private final String fileName;
    private final String contents;

    public EOTargetFile(String fileName, String contents) {
        this.fileName = fileName;
        this.contents = contents;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContents() {
        return contents;
    }
}
