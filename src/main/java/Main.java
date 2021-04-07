import transpiler.medium2target.Medium2TargetTranspiler;
import transpiler.mediumcodemodel.EOSourceEntity;
import transpiler.mediumcodemodel.EOSourceFile;
import transpiler.xml2medium.XML2MediumParser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/nlchar/eo-to-java-model/src/main/java/app.eo.xml");
        XML2MediumParser xml = new XML2MediumParser(file);
        try {
            EOSourceEntity smth = xml.parse();
            Medium2TargetTranspiler.transpile((EOSourceFile) smth);
        } catch (XML2MediumParser.XML2MediumParserException e) {
            e.printStackTrace();
        }
    }
}
