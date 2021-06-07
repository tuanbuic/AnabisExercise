package vn.anibis.core;

public interface Action {
    String getStringWithParams(String templateString, String... params);
}
