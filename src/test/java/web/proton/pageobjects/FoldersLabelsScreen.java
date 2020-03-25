package web.proton.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FoldersLabelsScreen extends POBase {

    @FindBy(css = ADD_LABEL_BUTTON)
    private WebElement addLabelButton;

    @FindBy(css = LABEL_ITEMS)
    private List<WebElement> labelItems;

    public FoldersLabelsScreen(WebDriver driver) {
        this.driver = driver;
        waitForVisibility(ADD_LABEL_BUTTON);
    }

    public static FoldersLabelsScreen getInstance(WebDriver driver) {
        FoldersLabelsScreen foldersLabelsScreen = new FoldersLabelsScreen(driver);
        PageFactory.initElements(driver, foldersLabelsScreen);
        return foldersLabelsScreen;
    }

    public AddLabelScreen navigateToAddLabel() {
        addLabelButton.click();
        return AddLabelScreen.getInstance(driver);
    }

    public FoldersLabelsScreen deleteLabel() {
        findElementByCss(LABEL_ITEM_DROPDOWN).click();
        findElementByCss(LABEL_ITEM_DELETE).click();
        findElementByCss(LABEL_DELETE_CONFIRM).submit();
        return FoldersLabelsScreen.getInstance(driver);
    }

    public AddLabelScreen editLabel() {
        findElementByCss(LABEL_ITEM_EDIT).click();
        return AddLabelScreen.getInstance(driver);
    }

    public FoldersLabelsScreen verifyLabelExists(String labelName) {
        findElementByText(labelName, "span", driver);
        return this;
    }

    public FoldersLabelsScreen verifyLabelNotExists(String labelName) {
        waitForElementToDisappear(findElementByCss(LABEL_ITEM_DROPDOWN));
        List<WebElement> labels = findElementsByText(labelName, "span", driver);
        Assert.assertTrue(String.format("Label %s was found, but should have been deleted", labelName), labels.isEmpty());
        return this;
    }
}