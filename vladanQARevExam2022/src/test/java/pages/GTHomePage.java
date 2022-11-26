package pages;

import helpers.BaseHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class GTHomePage extends BaseHelper {

    WebDriver driver;
    public GTHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (className="search-icon")
    WebElement searchIcon;

    @FindBy (className="primary")
    WebElement acceptCookiesButton;


    @FindBy (id="searchbox")
    WebElement searchBox;

    @FindBy (className="hd-reg")
    WebElement logInButton;


    private void navigateToHomePage(String url)
    {
        driver.get(url);
    }

    private void acceptCookies() throws InterruptedException {
        List<WebElement> cookiesAcceptButton = driver.findElements(By.className("primary"));
        if (cookiesAcceptButton.size() != 0) {
            wdWait.until(ExpectedConditions.elementToBeClickable(cookiesAcceptButton.get(0)));
            cookiesAcceptButton.get(0).click();
            wdWait.until(ExpectedConditions.invisibilityOf(cookiesAcceptButton.get(0)));
        }
    }

    private void enterSearchTerm(String product) throws InterruptedException {

        wdWait.until(ExpectedConditions.invisibilityOf(acceptCookiesButton));

        WebElement searchContainer= searchBox.findElement(By.className("search-container"));
        WebElement inputField= searchContainer.findElement(By.tagName("input"));

        inputField.click();
        inputField.sendKeys(product);
    }

    private void clickOnSearchButton(){
        wdWait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        searchIcon.click();
    }

    private void clickOnLoginButton(){
        wdWait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
    }


    public void mainProductSearch(String url, String product) throws InterruptedException {
        navigateToHomePage(url);
        Thread.sleep(5000);
        acceptCookies();
        enterSearchTerm(product);
        clickOnSearchButton();
    }

    public void navigateToLoginPage(String url) throws InterruptedException {
        navigateToHomePage(url);
        Thread.sleep(5000);
        acceptCookies();
        clickOnLoginButton();
    }
}
