package vn.anibis.page;

import org.apache.log4j.Logger;
import vn.anibis.builder.ActionBuilder;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.ActionType;
import vn.anibis.core.web.WebAction;

public class BasePage {
    protected WebAction webAction;
    private Logger logger = Logger.getLogger(getClass());
    public BasePage(){
        try {
            String platformName = Configuration.instance().getValue(Configuration.WEB_BROWSER_NAME);
            if(platformName!=null && !platformName.isEmpty()){
                setWebAction(ActionType.valueOf(platformName.toUpperCase()));
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
    public void setWebAction(ActionType type){
        webAction = (WebAction) ActionBuilder.buildAction(type);
    }
}
