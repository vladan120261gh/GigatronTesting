package pages;

import helpers.BaseHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GTPersonalDataPage extends BaseHelper {

    WebDriver driver;
    public GTPersonalDataPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy (name="phone")
    WebElement phoneField;

    @FindBy (className = "profile-card")
    WebElement saveButtonHolder;

    private void updatePhoneNumber(){
        wdWait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.clear();
        phoneField.sendKeys(generateRandomPhone());

    }

    private String generateRandomPhone(){
        int randomPhone= (int)Math.floor(Math.random()*9000000 + 1000000);
        String randomPhoneString= "064" + randomPhone;

        return randomPhoneString;
    }

    private void clickOnSaveButton(){
        WebElement saveButton= saveButtonHolder.findElement(By.tagName("button"));
        saveButton.click();
    }

    public void updatePhoneAndSave(){
        updatePhoneNumber();
        clickOnSaveButton();
    }




}
