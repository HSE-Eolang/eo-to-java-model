import java.io.File;
import transpiler.xml2medium.XML2MediumParser;
import transpiler.mediumcodemodel.EOSourceEntity;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/nlchar/eo-to-java-model/src/main/java/app.eo.xml");
        XML2MediumParser xml = new XML2MediumParser(file);
        try {
            EOSourceEntity smth = xml.parse();
        } catch (XML2MediumParser.XML2MediumParserException e) {
            e.printStackTrace();
        }
    }
}
