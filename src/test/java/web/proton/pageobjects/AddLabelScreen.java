package web.proton.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddLabelScreen extends POBase {

    @FindBy(css = LABEL_NAME_IF)
    private WebElement labelName;

    @FindBy(css = COLORS)
    private List<WebElement> colors;

    @FindBy(css = SUBMIT_BUTTON)
    private WebElement submitButton;

    public AddLabelScreen(WebDriver driver) {
        this.driver = driver;
        waitForVisibility();
    }
    public static AddLabelScreen getInstance(WebDriver driver) {
        AddLabelScreen addLabelScreen = new AddLabelScreen(driver);
        PageFactory.initElements(driver, addLabelScreen);
        return addLabelScreen;
    }

    public FoldersLabelsScreen createLabel(String name) {
        labelName.clear();
        labelName.sendKeys(name);
        colors.get(0).click();
        submitButton.submit();
        waitForVisibility(LABEL_ITEMS);
        return FoldersLabelsScreen.getInstance(driver);
    }
}
