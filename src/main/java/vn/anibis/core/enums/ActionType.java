package vn.anibis.core.enums;

public enum ActionType {
    CHROME("CHROME"),
    EDGE("EDGE"),
    FIREFOX("FIREFOX"),
    IE("IE"),
    SAFARI("SAFARI"),
    ANDROID("ANDROID"),
    APP_CENTER_ANDROID("APP_CENTER_ANDROID"),
    APP_CENTER_IOS("APP_CENTER_IOS"),
    IOS("IOS"),
    SQL("SQL"),
    REST("REST"),
    SOAP("SOAP"),
    COMMON("COMMON");
    String browser;
    private ActionType(String browser){
        this.browser = browser;
    }
    public String getValue() {
        return browser;
    }
}
