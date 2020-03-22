package vn.anibis.test.pages;


public class HomePage extends CommonPage {
    public void goToPage(String page) {
        switch (page.toUpperCase()) {
            case "HOME":
                gotoURL(getDataValue("anibis.home.url"));
                break;
            case "LOGIN":
                gotoURL(getDataValue("anibis.login.url"));
                break;
            case "SEARCH":
                gotoURL(getDataValue("anibis.search.url"));
                break;
            default:
                logger.info("Cannot find your URL");
                break;
        }

    }

    public void clickLogin() throws Exception {
        click("homepage.login.button");
    }

    public void backToHomePage() throws Exception {
        click("homePage.icon");
    }
}
