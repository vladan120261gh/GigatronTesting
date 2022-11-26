package pages;

import helpers.BaseHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class GTProfileMainPage extends BaseHelper {

    WebDriver driver;
    public GTProfileMainPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy (className="v-nav")
    WebElement leftNav;


    public void navigateToPersonalDataPage(){
        wdWait.until(ExpectedConditions.visibilityOf(leftNav));
        List<WebElement> leftNavItems= leftNav.findElements(By.tagName("li"));

        for (WebElement item : leftNavItems){
            if (item.getText().contains("Lični podaci i lozinka")){
                item.click();
                break;
            }
        }
    }




}
