package vn.anibis.util;

import java.util.Map;

public class StringUtil {
    static final String PARAMS = "{{PARAM%d}}";
    static final String PARAM_TEMPLATE = "\\{\\{%s\\}\\}";
    public static String getStringWithParams(String tempalteSring, String...params){
        if(tempalteSring ==null||tempalteSring.isEmpty()||params==null||params.length==0){
            return tempalteSring;
        }
        for(int i = 0 ;i<params.length;i++){
            String p = params[i];
            if(p!=null && !p.isEmpty()){
                tempalteSring = tempalteSring.replace(String.format(PARAMS,i+1),p);
            }
        }
        return tempalteSring;
    }
    public static String replaceStringWithMap(String source , Map<String, String> map){
        if(source==null||source.isEmpty()||map==null||map.size()==0){
            return source;
        }
        final String[] inputCopy = {source};

        map.forEach((key,value)->{
            if(value!=null){
                inputCopy[0]= inputCopy[0].replaceAll(String.format(PARAM_TEMPLATE,key),value);
            }
        });
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            inputCopy[0]= inputCopy[0].replaceAll(String.format(PARAM_TEMPLATE,entry.getKey()),entry.getValue());
//        }
        return inputCopy[0];
    }

}
