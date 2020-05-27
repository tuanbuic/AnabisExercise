package vn.anibis.util;

public class StringUtil {
    static final String PARAMS = "{{PARAM%d}}";
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
}
