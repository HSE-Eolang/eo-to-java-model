package transpiler.mediumcodemodel;

import transpiler.targetjavamodel.TargetJavaEntity;
import java.util.ArrayList;
import java.util.Optional;

public class EOAbstraction extends EOSourceEntity {

    private Optional<String> instanceName;

    private ArrayList<EOInputAttribute> freeAttributes;

    public void setApplications(ArrayList<EOApplication> applications) {
        this.applications = applications;
    }

    private ArrayList<EOApplication> applications;

    private String xmlName;

    public EOAbstraction(String xmlName, Optional<String> instanceName, ArrayList<EOInputAttribute> freeAttributes) {
        this.xmlName = xmlName;
        this.instanceName = instanceName;
        this.freeAttributes = freeAttributes;
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

    @Override
    public TargetJavaEntity transpile() {
        return null;
    }
}
