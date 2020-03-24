package web.proton.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class POBase {

    public static final int DEFAULT_TIMEOUT = 30;
    // Login page
    public static final String USERNAME_IF = "input[id='username']";
    public static final String PASSWORD_IF = "input[name='password']";
    public static final String LOGIN_BUTTON = "button[id='login_btn']";


    // Main page
    public static final String CLOSE_NOTIFICATION_BUTTON = "button[title='Close']";
    public static final String LABELS_LINK = "a[href$='labels']";


    // Folder/Label page
    public static final String ADD_LABEL_BUTTON = "button[data-test-id='folders/labels:addLabel']";
    public static final String LABEL_ITEMS = "tr[data-test-id='folders/labels:item-type:label']";
    public static final String LABEL_ITEM_DROPDOWN = "button[data-test-id='dropdown:open']";
    public static final String LABEL_ITEM_DELETE = "button[data-test-id='folders/labels:item-delete']";
    public static final String LABEL_DELETE_CONFIRM = "button[type='submit']";
    public static final String LABEL_ITEM_EDIT = "button[data-test-id='folders/labels:item-edit']";

    // Labels page
    public static final String LABEL_NAME_IF = "input[data-test-id='label/folder-modal:name']";
    public static final String COLORS = "li[class='ColorSelector-item']";
    public static final String SUBMIT_BUTTON = "button[type='submit']";

    public WebDriver driver;


    public void waitForVisibility(String... locators) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        for (String locator : locators) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        }
    }

    public WebElement findElementByText(String text, String attribute, WebDriver driver) {
        String xpath = String.format("//%s[contains(text(),'%s')]", attribute, text);
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    // this method is used to check if element does not exist -> list.isEmpty()
    public List<WebElement> findElementsByText(String text, String attribute, WebDriver driver) {
        String xpath = String.format("//%s[contains(text(),'%s')]", attribute, text);
        return driver.findElements(By.xpath(xpath));
    }

    public WebElement findElementByCss(String locator) {
        waitForVisibility(locator);
        return driver.findElement(By.cssSelector(locator));
    }

    public WebElement findNestedElementByCss(String locator, WebElement webElement) {
        waitForVisibility(locator);
        return webElement.findElement(By.cssSelector(locator));
    }
}
