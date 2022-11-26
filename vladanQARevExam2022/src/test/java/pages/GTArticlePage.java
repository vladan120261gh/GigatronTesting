package pages;

import com.google.common.collect.Comparators;
import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GTArticlePage extends BaseHelper {

    WebDriver driver;
    public GTArticlePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className="product-main-price")
    WebElement priceHolder;

    private float fetchPrice(){
        wdWait.until(ExpectedConditions.visibilityOf(priceHolder));
        WebElement price= priceHolder.findElement(By.className("ppra_price-number"));
        float priceFloat= Float.parseFloat(price.getText().replace(".", ""));

        return priceFloat;
    }

    private float fetchPoints(){
        WebElement points= priceHolder.findElement(By.className("right-points-icon-and-number"));
        float pointsFloat= Float.parseFloat(points.getText());

        return pointsFloat;
    }

    public boolean pointsCorrectlyCalculated(){
        return (Math.round(fetchPrice()/1000)) == (Math.round(fetchPoints()));
    }



}
