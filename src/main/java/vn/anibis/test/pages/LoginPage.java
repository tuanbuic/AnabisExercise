package vn.anibis.test.pages;

public class LoginPage extends CommonPage {
    public void logInWithAccount(String username, String password) throws Exception {
        typeText("login.username.textbox", getDataValue(username));
        click("login.continue.button");
        typeText("login.password.textbox", getDataValue(password));
        click("login.logOn.button");
    }

    public void inputUsername(String username) {
        typeText("login.username.textbox", username);
    }

    public void clickSubmit() throws Exception {
        click("login.continue.button");
    }
}
