package vn.anibis.builder;

import vn.anibis.core.enums.ActionType;
import vn.anibis.core.web.Action;
import vn.anibis.core.web.WebAction;

public class ActionBuilder {
    public static Action buildAction(ActionType type) {
        try {
            switch (type){
                case CHROME:
                    return (Action) Class.forName("vn.anibis.testrunner.hook.browsers.ChromeActionImpl").newInstance();
                default:
                    return null;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException cne) {
            return null;
        }
    }
}
