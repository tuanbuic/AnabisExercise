package vn.anibis.core.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import vn.anibis.core.api.enums.HttpMethodEnum;
import vn.anibis.core.api.model.PostmanRequestObject;
import vn.anibis.core.config.Configuration;
import vn.anibis.util.FilesUtil;

import java.util.Map;

public class RestClient {
    static final String SERENITY_REST_ASSURED_CLASS = "SerenityRest";
    public static Response callApi(PostmanRequestObject requestObject) throws Exception {
        Response req = null;
        RequestSpecification reqSpec;
        if (SERENITY_REST_ASSURED_CLASS.equalsIgnoreCase(Configuration.instance().getValue(Configuration.REST_ASSURED_CLASS))) {
            reqSpec = SerenityRest.given().relaxedHTTPSValidation().headers(requestObject.getHeaders());
        } else {
            reqSpec = RestAssured.given().relaxedHTTPSValidation().headers(requestObject.getHeaders());
        }

        if ("basic".equalsIgnoreCase(requestObject.getAuth())) {
            reqSpec = reqSpec.auth().preemptive().basic(requestObject.getUsername(), requestObject.getPasword());
        }

        switch (HttpMethodEnum.valueOf(requestObject.getMethod().toUpperCase())) {
            case GET:
                req = reqSpec.body(requestObject.getBody()).get(requestObject.getUrl());
                break;
            case POST:
                req = reqSpec.body(requestObject.getBody()).post(requestObject.getUrl());
                break;
            case PUT:
                req = reqSpec.body(requestObject.getBody()).put(requestObject.getUrl());
                break;
            case DELETE:
                req = reqSpec.body(requestObject.getBody()).delete(requestObject.getUrl());
                break;
            case PATCH:
                req = reqSpec.body(requestObject.getBody()).patch(requestObject.getUrl());
                break;
            case OPTIONS:
                req = reqSpec.body(requestObject.getBody()).options(requestObject.getUrl());
                break;
            default:
                break;
        }
        return req;
    }
}
