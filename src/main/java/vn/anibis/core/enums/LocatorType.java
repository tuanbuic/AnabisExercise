package vn.anibis.core.enums;

public enum LocatorType {
    ACCESSIBILITYID("ACCESSIBILITYID"),
    CLASSNAME("CLASSNAME"),
    CSSSELECTOR("CSSSELECTOR"),
    ID("ID"),
    LINKTEXT("LINKTEXT"),
    NAME("NAME"),
    PARTIALLINKTEXT("PARTIALLINKTEXT"),
    TAGNAME("TAGNAME"),
    XPATH("XPATH");

    String locatorTypeString;

    LocatorType(String locatorTypeString) {
        this.locatorTypeString = locatorTypeString;
    }


    public String getValue() {
        return locatorTypeString;
    }
}
