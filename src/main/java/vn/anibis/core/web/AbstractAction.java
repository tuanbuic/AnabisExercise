package vn.anibis.core.web;

import vn.anibis.util.StringUtil;

public abstract class AbstractAction implements Action {
    @Override
    public String getStringWithParams(String templateString, String... params) {
        return StringUtil.getStringWithParams(templateString, params);
    }
}
