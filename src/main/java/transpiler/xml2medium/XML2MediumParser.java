package transpiler.xml2medium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.*;
import transpiler.mediumcodemodel.EOAbstraction;
import transpiler.mediumcodemodel.EOPackage;
import transpiler.mediumcodemodel.EOSourceEntity;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;
import transpiler.mediumcodemodel.EOSourceFile;

public class XML2MediumParser {

    private final File file;
    private XPath xPath;
    private Document doc;

    public XML2MediumParser(File file) {
        this.file = file;
    }

    public EOSourceEntity parse() throws XML2MediumParserException {
        loadXmlDocument();

        EOSourceFile sourceFile = FileMetadataParsingUtils.parseSourceFile(this.file, this.doc, this.xPath);

        ArrayList<EOSourceEntity> objects = ObjectsParsingUtils.parseObjects(this.file, this.doc, this.xPath);

        return new EOPackage("aaa");
    }

    private void loadXmlDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            this.doc = dBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.xPath = XPathFactory.newInstance().newXPath();
    }

    public static class XML2MediumParserException extends Exception {

        public XML2MediumParserException(String message) {
            super(message);
        }
    }
}
