package vn.anibis.test.pages;

public class LoginPage extends CommonPage {
    public void logInWithAccount(String username, String password) throws Exception {
        String account;
        String pass;
        if(getDataValue(username)==null){
            account = username;
        }else {
            account = getDataValue(username);
        }
        if(getDataValue(password)==null){
            pass = password;
        }else{
            pass = getDataValue(password);
        }
        typeText("login.username.textbox", account);
        click("login.continue.button");
        typeText("login.password.textbox", pass);
        click("login.logOn.button");
    }

    public void inputUsername(String username) {
        typeText("login.username.textbox", username);
    }

    public void clickSubmit() throws Exception {
        click("login.continue.button");
    }
}
