package vn.anibis.core.api.model;

import java.util.HashMap;
import java.util.Map;


import com.google.gson.JsonArray;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import net.minidev.json.JSONArray;
import org.apache.log4j.Logger;
import vn.anibis.util.StringUtil;


public class PostmanRequestObject {
    private static final Logger LOGGER = Logger.getLogger(PostmanRequestObject.class);
    static final String REQUEST_JSON_PATH = "$..item[?(@.request!=null && @.name=='%s')].request";
    static final String AUTH_BASIC_JSON_PATH = "$..auth.basic";
    static final String AUTH_BEARER_JSON_PATH = "$..auth.bearer";
    static final String AUTH_BASIC_USR_JSON_PATH = "$..auth.basic.username";
    static final String AUTH_BASIC_PWRD_JSON_PATH = "$..auth.basic.password";
    static final String BODY_FORM_JSON_PATH = "$..body.urlencoded";
    static final String BODY_RAW_JSON_PATH = "$..body.raw";
    static final String HEADER_JSON_PATH = "$..header";
    static final String METHOD_JSON_PATH = "$..method";
    static final String URL_RAW_JSON_PATH = "$..url.raw";
    static final String URL_JSON_PATH = "$..url";
    String auth;
    String url;
    String method;
    String body;
    String username;
    String password;
    Map<String, String> headers;

    public static PostmanRequestObject parse(String itemName, String collection, Map<String, String> env) {
        String auth = "";
        String url = "";
        String method = "";
        String body = "";
        String username = "";
        String password = "";
        Map<String, String> headers = new HashMap<>();
        PostmanRequestObject postmanRequestObject = new PostmanRequestObject();
        Configuration conf = Configuration.defaultConfiguration();
        conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        JSONArray objRequest = JsonPath.using(conf)
                .parse(collection)
                .read(String.format(REQUEST_JSON_PATH, itemName));
        if (objRequest == null) {
            LOGGER.error("Something wrong with json and jsondata. JSONObject is null.");
        }
        String requestString = conf.jsonProvider().toJson(objRequest.get(0));
        //Replace String
        requestString = StringUtil.replaceStringWithMap(requestString, env);
        ReadContext ctx = JsonPath.parse(requestString);
        //url
        JSONArray urlArray = ctx.read(URL_RAW_JSON_PATH);
        if (urlArray.isEmpty()) {
            urlArray = ctx.read(URL_JSON_PATH);
        }
        if (!urlArray.isEmpty()) {
            url = (urlArray.get(0).toString());
        }
        //header
        JSONArray headerArray = ctx.read(HEADER_JSON_PATH);
        if (!headerArray.isEmpty()) {
            for (Object object : (JSONArray) headerArray.get(0)) {
                if (object instanceof Map) {
                    Map<String, String> map = (Map<String, String>) object;
                    headers.put(map.get("key"), map.get("value"));
                }
            }
        }
        //body
        JSONArray bodyArray = ctx.read(BODY_RAW_JSON_PATH);
        if(!bodyArray.isEmpty()){
            body = bodyArray.get(0).toString();
        }
        //auth
        JSONArray authArray =  ctx.read(AUTH_BASIC_JSON_PATH);
        if(!authArray.isEmpty()){
            username=((JSONArray)ctx.read(AUTH_BASIC_USR_JSON_PATH)).get(0).toString();
            password=((JSONArray) ctx.read(AUTH_BASIC_PWRD_JSON_PATH)).get(0).toString();
            auth="basic";
            headers.remove("Authorization");

        }else{
            authArray = ctx.read(AUTH_BEARER_JSON_PATH);
            if (!authArray.isEmpty()) {
                headers.put("Authorization", authArray.get(0).toString());
            }
        }
        //method
        JSONArray methodArray = ctx.read(METHOD_JSON_PATH);
        method = methodArray.get(0).toString();
        postmanRequestObject.setUrl(url);
        postmanRequestObject.setAuth(auth);
        postmanRequestObject.setUsername(username);
        postmanRequestObject.setPasword(password);
        postmanRequestObject.setBody(body);
        postmanRequestObject.setHeaders(headers);
        postmanRequestObject.setMethod(method);

        return postmanRequestObject;
    }
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPasword() {
        return password;
    }

    public void setPasword(String pasword) {
        this.password = pasword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
