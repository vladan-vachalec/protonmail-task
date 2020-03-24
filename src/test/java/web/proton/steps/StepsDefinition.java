package web.proton.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import web.WebDriverFactory;
import web.proton.pageobjects.AddLabelScreen;
import web.proton.pageobjects.FoldersLabelsScreen;
import web.proton.pageobjects.LoginScreen;
import web.proton.pageobjects.MainMailScreen;

public class StepsDefinition {

    private final WebDriver driver = WebDriverFactory.createWebDriver();
    private LoginScreen loginScreen;
    private MainMailScreen mainMailScreen;
    private FoldersLabelsScreen foldersLabelsScreen;
    private AddLabelScreen addLabelScreen;

    private String labelName;

    @Given("ProtonMail website is opened")
    public void protonmailWebsiteIsOpened() {
        driver.get("https://beta.protonmail.com/login");
    }

    @And("user logs in with his ProtonMail credentials")
    public void userLogsInWithCredentials() {
        loginScreen = LoginScreen.getInstance(driver);
        mainMailScreen = loginScreen.loginToMail();
        mainMailScreen.dismissNotification();
    }

    @When("user navigates to Folders\\/labels")
    public void userNavigatesToFoldersLabels() {
        foldersLabelsScreen = mainMailScreen.navigateToFolderLabel();
    }

    @And("user creates a label with name: {string}")
    public void userCreatesALabelWithName(String newLabelName) {
        labelName = newLabelName;
        addLabelScreen = foldersLabelsScreen.navigateToAddLabel();
        foldersLabelsScreen = addLabelScreen.createLabel(labelName);
    }

    @Then("the label is created and present in the list of labels")
    public void theLabelIsCreatedAndPresentInTheListOfLabels() {
        foldersLabelsScreen.verifyLabelExists(labelName);
    }

    @When("user deletes the label")
    public void userDeletesALabelWithName() {
        foldersLabelsScreen.deleteLabel();
    }

    @Then("the label is removed from list of labels")
    public void theLabelIsRemovedFromListOfLabels() {
        foldersLabelsScreen.verifyLabelNotExists(labelName);
    }

    @When("user edits the label with: {string} suffix")
    public void userEditsTheLabelNameTo(String newLabelName) {
        labelName += newLabelName;
        addLabelScreen = foldersLabelsScreen.editLabel();
        addLabelScreen.createLabel(newLabelName);
    }

    @After()
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }
}
