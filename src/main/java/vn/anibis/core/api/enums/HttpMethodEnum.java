package vn.anibis.core.api.enums;

public enum HttpMethodEnum {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS");
    private final String text;

    HttpMethodEnum(final String text) {
        this.text = text;
    }
    public String getValue(){
        return text;
    }
    @Override
    public String toString() {
        return text;
    }
}
