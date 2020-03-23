package web.proton.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMailScreen extends POBase {

    @FindBy(css = CLOSE_NOTIFICATION_BUTTON)
    private WebElement closeNotificationButton;

    @FindBy(css = LABELS_LINK)
    private WebElement labelsLink;


    public MainMailScreen(WebDriver driver) {
        this.driver = driver;
        waitForVisibility(LABELS_LINK, CLOSE_NOTIFICATION_BUTTON);
    }

    public static MainMailScreen getInstance(WebDriver driver) {
        MainMailScreen mailScreen = new MainMailScreen(driver);
        PageFactory.initElements(driver, mailScreen);
        return mailScreen;
    }

    public MainMailScreen dismissNotification() {
        closeNotificationButton.click();
        return this;
    }

    public FoldersLabelsScreen navigateToFolderLabel() {
        labelsLink.click();
        return FoldersLabelsScreen.getInstance(driver);
    }
}
