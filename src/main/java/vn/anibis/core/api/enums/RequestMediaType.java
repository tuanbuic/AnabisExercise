package vn.anibis.core.api.enums;

public enum RequestMediaType {
    JSON("JSON"), XML("XML"), FORM("FORM");
    private String text;

    RequestMediaType(final String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
