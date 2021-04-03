package transpiler.xml2medium;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import transpiler.mediumcodemodel.EOAbstraction;
import transpiler.mediumcodemodel.EOApplication;
import transpiler.mediumcodemodel.EOInputAttribute;
import transpiler.mediumcodemodel.EOSourceEntity;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class ObjectsParsingUtils {
    private static final String NAME_ATTR = "name";
    private static final String ORIGINAL_NAME_ATTR = "original-name";
    private static final String LINE_ATTR = "line";
    private static final String VARARG_ATTR = "vararg";
    private static final String METHOD_ATTR = "method";
    private static final String BASE_ATTR = "base";

    public static ArrayList<EOSourceEntity> parseObjects(File file, Document doc, XPath xPath) throws XML2MediumParser.XML2MediumParserException {

        ArrayList<EOAbstraction> abstractions =  parseAbstractions(file, doc, xPath);

        return new ArrayList<>();
        /*NodeList objectsTags = getObjectsTags(file, doc, xPath);

        for (int i = 0; i < objectsTags.getLength(); i++) {
            Node object = objectsTags.item(i);
            System.out.println(isApplication(file, object, xPath));
        }

        return new ArrayList<>();*/
    }

    private static NodeList getObjectsTags(File file, Document doc, XPath xPath) throws XML2MediumParser.XML2MediumParserException {
        /* locating and retrieving the <objects> tag */
        NodeList objects;
        try {
            objects = (NodeList)xPath.evaluate
                    (
                            "/program/objects/o",
                            doc,
                            XPathConstants.NODESET
                    );
        } catch (Exception e) {
            throw new XML2MediumParser.XML2MediumParserException("Internal error occurred while parsing the <objects> tag contents in File " + file.getName() + ".");
        }

        if (objects.getLength() == 0) {
            throw new XML2MediumParser.XML2MediumParserException("File " + file.getName() + " contains no object declarations in the <objects> tag. There must be at least one object declaration in the <objects> tag.");
        }

        return objects;
    }

    private static ArrayList<EOAbstraction> parseAbstractions(File file, Document doc, XPath xPath) throws XML2MediumParser.XML2MediumParserException {
        ArrayList<EOAbstraction> abstractions = new ArrayList<>();
        NodeList abstractionDeclarations;
        try {
            abstractionDeclarations = (NodeList)xPath.evaluate
                    (
                            "/program/objects/o[./o[not(@base) or @name]]",
                            doc,
                            XPathConstants.NODESET
                    );
        } catch (Exception e) {
            throw new XML2MediumParser.XML2MediumParserException("Internal error occurred while parsing abstraction declarations in File " + file.getName() + ".");
        }

        for (int i = 0; i < abstractionDeclarations.getLength(); i++) {
            Node abstractionDeclaration = abstractionDeclarations.item(i);
            /* retrieving the number of the line */
            String lineNumber = parseLineNumber(file.getName(), abstractionDeclaration);
            /* retrieving the name attr (which contains a special name with hierarchy encoded into it) */
            String xmlName = parseAbstractionXMLName(file.getName(), lineNumber, abstractionDeclaration);
            /* retrieving the original-name attr (which contains the normal name) */
            Optional<String> name = parseAbstractionName(file.getName(), lineNumber, abstractionDeclaration);
            /* retrieving free attributes (if any is present) */
            ArrayList<EOInputAttribute> attributes = parseAbstractionFreeAttributes(file.getName(), lineNumber, abstractionDeclaration, xPath);

            EOAbstraction abstraction = new EOAbstraction(xmlName, name, attributes);
            ArrayList<EOApplication> applications = parseApplications(file, abstractionDeclaration, xPath, abstraction);
            abstraction.setApplications(applications);
            abstractions.add(abstraction);
        }



        return abstractions;
    }

    /***
     * Parses the 'line' attribute that contains the line number on which the {@code declaration} occurred.
     */
    private static String parseLineNumber(String filename, Node declaration) throws XML2MediumParser.XML2MediumParserException {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node lineNumberNode = declarationAttributes.getNamedItem(LINE_ATTR);
        if (lineNumberNode == null) {
            throw new XML2MediumParser.XML2MediumParserException("File " + filename + ": one of the declarations in the <objects> tag does not contain the required 'line' attribute.");
        }
        String lineNumber = lineNumberNode.getNodeValue();

        return lineNumber;
    }

    /***
     * Parses the 'name' attribute that contains the name of the abstraction {@code declaration} with a special structure reflecting objects hierarchy.
     */
    private static String parseAbstractionXMLName(String filename, String lineNumber, Node declaration) throws XML2MediumParser.XML2MediumParserException {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node xmlNameNode = declarationAttributes.getNamedItem(NAME_ATTR);
        if (xmlNameNode == null) {
            throw new XML2MediumParser.XML2MediumParserException("File " + filename + ", line #" + lineNumber + ": abstraction declaration does not contain the required 'name' attribute.");
        }
        String xmlName = xmlNameNode.getNodeValue();

        return xmlName;
    }

    /***
     * Parses the 'original-name' attribute that contains the normal name (i.e. in the form it is present in the source program)
     * of the abstraction {@code declaration}.
     */
    private static Optional<String> parseAbstractionName(String filename, String lineNumber, Node declaration) throws XML2MediumParser.XML2MediumParserException {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node nameNode = declarationAttributes.getNamedItem(ORIGINAL_NAME_ATTR);
        // it is optional. anonymous objects don't have the original-name
        Optional<String> name;
        if (nameNode == null) {
            name = Optional.empty();
        }
        else {
            name = Optional.of(nameNode.getNodeValue());
        }

        return name;
    }

    /***
     * Parses the free attributes of the abstraction {@code declaration}.
     */
    private static ArrayList<EOInputAttribute> parseAbstractionFreeAttributes(String filename, String lineNumber, Node abstractionDeclaration, XPath xPath) throws XML2MediumParser.XML2MediumParserException {
        ArrayList<EOInputAttribute> attributes = new ArrayList<>();
        NodeList attributesDeclarations;
        try {
            attributesDeclarations = (NodeList)xPath.evaluate
                    (
                            "o[not(@base or @as or @level) and @name]",
                            abstractionDeclaration,
                            XPathConstants.NODESET
                    );
        } catch (Exception e) {
            throw new XML2MediumParser.XML2MediumParserException("File " + filename + ", line #" + lineNumber + ": internal error occurred while parsing free attributes of the abstraction.");
        }

        for (int i = 0; i < attributesDeclarations.getLength(); i++) {
            Node attributeDeclaration = attributesDeclarations.item(i);
            NamedNodeMap declarationAttributes = attributeDeclaration.getAttributes();
            /* retrieving the name of the attribute */
            Node nameNode = declarationAttributes.getNamedItem(NAME_ATTR);
            if (nameNode == null) {
                throw new XML2MediumParser.XML2MediumParserException("File " + filename + ", line #" + lineNumber + ": free attribute declaration inside abstraction declaration does not contain the required 'name' attribute.");
            }
            String name = nameNode.getNodeValue();
            /* retrieving information if the attribute is variable-length */
            Node varargNode = declarationAttributes.getNamedItem(VARARG_ATTR);
            boolean vararg;
            if (varargNode == null) {
                vararg = false;
            }
            else {
                vararg = true;
            }

            attributes.add(new EOInputAttribute(name, vararg));

        }

        return attributes;
    }

    private static ArrayList<EOApplication> parseApplications(File file, Node abstractionDeclaration, XPath xPath, EOAbstraction baseAbstraction) throws XML2MediumParser.XML2MediumParserException {
        ArrayList<EOApplication> applications = new ArrayList<>();
        NodeList applicationDeclarations;
        try {
            applicationDeclarations = (NodeList)xPath.evaluate
                    (
                            "o[not(@as or @level) and @name and @base]",
                            abstractionDeclaration,
                            XPathConstants.NODESET
                    );
        } catch (Exception e) {
            throw new XML2MediumParser.XML2MediumParserException("Internal error occurred while parsing application declarations in File " + file.getName() + " for abstraction + " + baseAbstraction.getXmlName() + ".");
        }

        for (int i = 0; i < applicationDeclarations.getLength(); i++) {
            Node applicationDeclaration = applicationDeclarations.item(i);
            EOApplication application = parseApplicationRecursively(file.getName(), xPath, applicationDeclaration);
            application.setScope(baseAbstraction);
            applications.add(application);
        }

        return applications;
    }

    private static EOApplication parseApplicationRecursively(String fileName, XPath xPath, Node application) throws XML2MediumParser.XML2MediumParserException {
        String lineNumber = parseLineNumber(fileName, application);
        String base = parseApplicationBase(fileName, lineNumber, application);
        boolean isDotNotation = parseHasMethodAttr(application) || base.startsWith(".");
        Optional<String> name = parseApplicationName(application);
        if (name.isPresent() && isDotNotation) {
            name = Optional.of(name.get().replaceAll("\\.", ""));
        }
        EOApplication eoApplication = new EOApplication(isDotNotation, base, name);

        ArrayList<EOApplication> arguments = new ArrayList<>();

        NodeList argumentsDeclarations;
        try {
            argumentsDeclarations = (NodeList)xPath.evaluate
                    (
                            "o[not(@as or @level) and @base]",
                            application,
                            XPathConstants.NODESET
                    );
        } catch (Exception e) {
            throw new XML2MediumParser.XML2MediumParserException("Internal error occurred while parsing application argument declarations in File " + fileName + " at line + " + lineNumber + ".");
        }

        for (int i = 0; i < argumentsDeclarations.getLength(); i++) {
            Node applicationDeclaration = argumentsDeclarations.item(i);
            EOApplication argumentApplication = parseApplicationRecursively(fileName, xPath, applicationDeclaration);
            argumentApplication.setScope(eoApplication);
            arguments.add(argumentApplication);
        }

        EOApplication dotNotationBase = null;
        if (isDotNotation) {
            dotNotationBase = arguments.get(0);
            arguments.remove(0);
        }

        eoApplication.setDotNotationBase(dotNotationBase);
        eoApplication.setArguments(arguments);

        return eoApplication;
    }

    /***
     * Parses the 'name' attribute that contains the normal name (i.e. in the form it is present in the source program)
     * of the application {@code declaration}.
     */
    private static Optional<String> parseApplicationName(Node declaration) {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node nameNode = declarationAttributes.getNamedItem(NAME_ATTR);
        // name is optional for application
        Optional<String> name;
        if (nameNode == null) {
            name = Optional.empty();
        }
        else {
            name = Optional.of(nameNode.getNodeValue());
        }

        return name;
    }

    /***
     * Checks if the {@code declaration} node has the 'method' attribute.
     */
    private static boolean parseHasMethodAttr(Node declaration) {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node methodNode = declarationAttributes.getNamedItem(METHOD_ATTR);
        if (methodNode == null) {
            return false;
        }
        else {
            return true;
        }
    }

    private static String parseApplicationBase(String filename, String lineNumber, Node declaration) throws XML2MediumParser.XML2MediumParserException {
        NamedNodeMap declarationAttributes = declaration.getAttributes();
        Node baseNode = declarationAttributes.getNamedItem(BASE_ATTR);
        if (baseNode == null) {
            throw new XML2MediumParser.XML2MediumParserException("File " + filename + ", line #" + lineNumber + ": application declaration does not contain the required 'base' attribute.");
        }
        String base = baseNode.getNodeValue();

        return base;
    }
}
