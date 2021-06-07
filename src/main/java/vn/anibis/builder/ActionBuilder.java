package vn.anibis.builder;

import vn.anibis.core.enums.ActionType;
import vn.anibis.core.Action;

public class ActionBuilder {
    public static Action buildAction(ActionType type) {
        try {
            switch (type){
                case CHROME:
                    return (Action) Class.forName("vn.anibis.browsers.ChromeActionImpl").newInstance();
                case FIREFOX:
                    return (Action) Class.forName("vn.anibis.browsers.FirefoxActionImpl").newInstance();
                default:
                    return null;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException cne) {
            return null;
        }
    }
}
