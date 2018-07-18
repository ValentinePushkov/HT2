package WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Objects;


public class PageObject {


    @FindBy(name = "j_username")
    private WebElement loginForLogIn;

    @FindBy(name = "j_password")
    private WebElement passForLogIn;

    @FindBy(id = "yui-gen1-button")
    private WebElement buttonForLogIn;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement linkManageJenkins;

    @FindBy(xpath = "//dt[text()='Manage User']")
    private WebElement dtManageUser;

    @FindBy(xpath = "//dd[text() = 'Create/delete/modify users that can log in to this Jenkins']")
    private WebElement ddCreateUser;

    @FindBy(xpath = "//a[text() = 'Create User']")
    private WebElement linkCreateUser;

    @FindBy(xpath = "//input[@name='username' and @type = 'text']")
    private WebElement fieldNewUserName;

    @FindBy(xpath = "//input[@name='password1'and @type = 'password']")
    private WebElement fieldNewUserPass1;

    @FindBy(xpath = "//input[@type='password'and @name = 'password2']")
    private WebElement fieldNewUserPass2;

    @FindBy(xpath = "//input[@type='text'and @name = 'fullname']")
    private WebElement fieldNewUserFullname;

    @FindBy(xpath = "//input[@type='text'and @name = 'email']")
    private WebElement fieldNewUserEmail;

    @FindBy(xpath = "//button[@type = 'button' and text() = 'Create User']")
    private  WebElement buttonCreateNewUser;

    @FindBy(xpath = "//tr/td/a[text() = 'someuser']")
    private WebElement tdNewUser;

    
    @FindBy(xpath = "//a[contains(@href, 'user/someuser/delete')]")
    private WebElement linkDeleteNewUser;

    @FindBy(xpath = "//a[@href = 'user/admin/delete']")
    private WebElement linkDeleteAdmin;


    @FindBy(xpath = "//form[text() = '\n" +
            "        Are you sure about deleting the user from Jenkins?\n" +
            "        ']")
          
    private WebElement formAreYouSure;

    @FindBy(xpath = "//button[@id = 'yui-gen1-button' and text() = 'Yes']")//Да
    private WebElement buttonDeleteUser;

    @FindBy(xpath = "//div[@class = 'error' and text() = '\"\" is prohibited as a full name for security reasons.']")
    private WebElement divError;

    @FindBy(xpath = "//a[text() = 'ENABLE AUTO REFRESH']")
    private WebElement linkEnableAutoRefresh;

    @FindBy(xpath = "//a[text() = 'DISABLE AUTO REFRESH']")
    private WebElement linkDisableAutoRefresh;

    @FindBy(xpath = "//a[@href = '/logout']")
    private WebElement linkLogout;

    private String Username = "someuser";
    private String Password = "somepassword";
    private String ConfirmPassword = "somepassword";
    private String FullName = "Some Full Name";
    private String EmailAddress = "some@addr.dom";


    public void login(String login, String password) {
       if (isElementPresent(loginForLogIn)) {
            loginForLogIn.clear();
            loginForLogIn.sendKeys(login);
            passForLogIn.clear();
            passForLogIn.sendKeys(password);
            buttonForLogIn.click();
        }
    }

    public boolean clickManageJenkins(){
        if (isElementPresent(linkManageJenkins)) {
            linkManageJenkins.click();
            return true;
        }
        return false;
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isThereManageUserdt() {
           return isElementPresent(dtManageUser);
    }

    public boolean isThereCreateJenkinsdd() {
        return isElementPresent(ddCreateUser);
    }

    public boolean clickManageUser() {
        if(isElementPresent(dtManageUser)){
            dtManageUser.click();
            return true;
        }
        return false;
    }

    public boolean isThereCreateUserlink() {
        return isElementPresent(linkCreateUser);
    }

    public boolean isThereFieldCreateUser() {
        if(!isElementPresent(fieldNewUserName)) return false;
        if(!isElementPresent(fieldNewUserPass1)) return false;
        if(!isElementPresent(fieldNewUserPass2)) return false;
        if(!isElementPresent(fieldNewUserFullname)) return false;
        if(!isElementPresent(fieldNewUserEmail)) return false;
        return true;
    }

    public boolean clickCreateUser() {
        if(isElementPresent(linkCreateUser)){
            linkCreateUser.click();
            return true;
        }
        return false;
    }

    public boolean isFieldCreateUserEmpty() {
        if(!fieldNewUserName.getText().isEmpty()) return false;
        if(!fieldNewUserPass1.getText().isEmpty()) return false;
        if(!fieldNewUserPass2.getText().isEmpty()) return false;
        if(!fieldNewUserFullname.getText().isEmpty()) return false;
        if(!fieldNewUserEmail.getText().isEmpty()) return false;
        return true;
    }

    public void inputDataToCreateUser() {
        inputData(fieldNewUserName,Username);
        inputData(fieldNewUserPass1,Password);
        inputData(fieldNewUserPass2,ConfirmPassword);
        inputData(fieldNewUserFullname,FullName);
        inputData(fieldNewUserEmail,EmailAddress);
    }

    private void inputData(WebElement webElement, String string){
        webElement.clear();
        webElement.sendKeys(string);
    }

    public boolean clickCreateNewUser() {
        if(isElementPresent(buttonCreateNewUser)){
            buttonCreateNewUser.click();
            return true;

        }
        return false;
    }

    public boolean isThereNewUser() {
        return isElementPresent(tdNewUser);
    }

    public boolean clickLinkDelete() {
        if(isElementPresent(linkDeleteNewUser)){
            linkDeleteNewUser.click();
            return true;
        }
        return false;
    }

    public boolean isThereDeleteUserText() {
        return isElementPresent(formAreYouSure);
    }

    public boolean clickButtonDeleteUser() {
        if (isElementPresent(buttonDeleteUser)) {
            buttonDeleteUser.click();
            return true;
        }
        return false;
    }

    public boolean isThereLinkDeleteAdmin() {
        return isElementPresent(linkDeleteAdmin);
    }

    public boolean checkCreateUsereButtonColor() {
       return checkButtonColor(buttonCreateNewUser);
    }

    public boolean checkDeleteUsereButtonColor() {
        return checkButtonColor(buttonDeleteUser);
    }

    private boolean checkButtonColor(WebElement webElement){
        if(Objects.equals(webElement.getCssValue("color"), "#4b758b")) return true;
        return false;
    }

    public boolean isThereDivError() {
        return isElementPresent(divError);
    }

    public boolean disableToEnable() {
        return chengeRefresh(linkDisableAutoRefresh,linkEnableAutoRefresh);
    }

    public boolean enableToDisable() {
        return chengeRefresh(linkEnableAutoRefresh,linkDisableAutoRefresh);
    }

    private boolean chengeRefresh(WebElement a,WebElement b){
        if(isElementPresent(a)){
            a.click();
            if(isElementPresent(b))
                return true;
            return false;
        }
        return false;
    }

    public void logout() {
        if (isElementPresent(linkLogout)) {
            linkLogout.click();
        }
    }
}
