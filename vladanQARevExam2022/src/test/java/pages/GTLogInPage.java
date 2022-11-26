package pages;

import helpers.BaseHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class GTLogInPage extends BaseHelper {

    WebDriver driver;
    public GTLogInPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy (id="email")
    WebElement emailField;

    @FindBy (id="password")
    WebElement passwordField;

    @FindBy (id="loginSubmit")
    WebElement submitButton;

    private void enterEmail(String email){
        wdWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    private void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    private void clickOnSubmit(){
        submitButton.click();
    }

    public void enterCredentialsAndSubmit(String email, String password){
        enterEmail(email);
        enterPassword(password);
        clickOnSubmit();
    }




}
