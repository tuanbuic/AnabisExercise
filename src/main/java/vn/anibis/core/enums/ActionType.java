package vn.anibis.core.enums;

public enum ActionType {
    CHROME("CHROME"),
    FIREFOX("FIREFOX");
    String browser;
    private ActionType(String browser){
        this.browser = browser;
    }
}
