package web.proton.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen extends POBase {

    @FindBy(css = USERNAME_IF)
    private WebElement usernameIF;

    @FindBy(css = PASSWORD_IF)
    private WebElement passwordIF;

    @FindBy(css = LOGIN_BUTTON)
    private WebElement loginButton;


    public static LoginScreen navigateTo(WebDriver driver, String url) {
        driver.navigate().to(url);
        LoginScreen loginScreen = new LoginScreen(driver);
        PageFactory.initElements(driver, loginScreen);
        return LoginScreen.getInstance(driver);
    }

    public static LoginScreen getInstance(WebDriver driver) {
        LoginScreen loginScreen = new LoginScreen(driver);
        PageFactory.initElements(driver, loginScreen);
        return loginScreen;
    }

    public LoginScreen(WebDriver driver) {
        this.driver = driver;
        waitForVisibility(USERNAME_IF, PASSWORD_IF, LOGIN_BUTTON);
    }

    public MainMailScreen loginToMail() {
        usernameIF.clear();
        usernameIF.sendKeys(System.getProperty("protonUsername"));
        passwordIF.clear();
        passwordIF.sendKeys(System.getProperty("protonPassword"));
        loginButton.submit();
        return MainMailScreen.getInstance(driver);
    }
}
