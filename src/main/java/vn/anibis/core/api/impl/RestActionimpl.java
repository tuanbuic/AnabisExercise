package vn.anibis.core.api.impl;

import io.restassured.specification.RequestSpecification;
import vn.anibis.core.api.RestAction;
import vn.anibis.core.api.client.RestClient;
import vn.anibis.core.api.enums.HttpMethodEnum;
import vn.anibis.core.api.model.PostmanRequestObject;
import vn.anibis.core.web.AbstractWebAction;
import vn.anibis.util.FilesUtil;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

public class RestActionimpl extends AbstractWebAction implements RestAction {


    @Override
    public Response doCallAPIPostMan(String itemName, String collection, Map<String, String> env) throws Exception {
        return RestClient.callApi(PostmanRequestObject.parse(collection,itemName,env));
    }

    @Override
    public Response doCallAPI(String host, String body, String method, String path, Map<String, String> headers, String auth, String username, String password) throws Exception {
        PostmanRequestObject postmanRequestObject = new PostmanRequestObject();
        postmanRequestObject.setUrl(host+path);
        postmanRequestObject.setHeaders(headers);
        postmanRequestObject.setMethod(method);
        postmanRequestObject.setBody(body);
        postmanRequestObject.setAuth(auth);
        postmanRequestObject.setUsername(username);
        postmanRequestObject.setPasword(password);
        return RestClient.callApi(postmanRequestObject);
    }
}
