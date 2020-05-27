package vn.anibis.core.web;

public interface Action {
    String getStringWithParams(String templateString, String... params);
}
