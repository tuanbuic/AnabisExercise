package vn.anibis.core.api;

import io.restassured.response.Response;
import vn.anibis.core.Action;

import java.util.Map;

public interface RestAction extends Action {
    Response doCallAPIPostMan(String itemName, String collection, Map<String, String> env ) throws Exception;
    Response doCallAPI(String host,String body, String method, String path, Map<String,String> headers, String auth, String username, String password) throws Exception;
}
