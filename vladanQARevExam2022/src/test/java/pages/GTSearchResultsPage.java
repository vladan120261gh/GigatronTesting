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

public class GTSearchResultsPage extends BaseHelper {

    WebDriver driver;
    public GTSearchResultsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="subcategories")
    WebElement subcategoriesSection;

    @FindBy(className="control-bar")
    WebElement controlBar;

    @FindBy(id="filters-Tip procesora")
    WebElement processorTypeSection;


    @FindBy(id="filters-RAM (memorija)")
    WebElement memorySelection;

    @FindBy(name="sort")
    WebElement sortingDropdown;


    private void selectCategory(String category){
        wdWait.until(ExpectedConditions.visibilityOf(subcategoriesSection));
        WebElement filters= subcategoriesSection.findElement(By.className("filters"));
        List<WebElement> categoryFilters= filters.findElements(By.className("filter"));

        String numOfResultsPriorFilter= controlBar.getText();

        for (WebElement filter : categoryFilters){
            if (filter.getText().toLowerCase().contains(category.toLowerCase())){
                filter.click();
                break;
            }
        }

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("control-bar"), numOfResultsPriorFilter));
    }

    private void selectProcessor(String processor){
        wdWait.until(ExpectedConditions.visibilityOf(processorTypeSection));
        WebElement filters= processorTypeSection.findElement(By.className("filters"));
        List<WebElement> processorFilters= filters.findElements(By.className("filter"));

        String numOfResultsPriorFilter= controlBar.getText();

        for (WebElement filter : processorFilters){
            if (filter.getText().toLowerCase().contains(processor.toLowerCase())){
                filter.click();
                break;
            }
        }

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("control-bar"), numOfResultsPriorFilter));
    }

    private void selectMemory(String memory){
        wdWait.until(ExpectedConditions.visibilityOf(memorySelection));
        WebElement filters= memorySelection.findElement(By.className("filters"));
        List<WebElement> memoryFilters= filters.findElements(By.className("filter"));

        String numOfResultsPriorFilter= controlBar.getText();

        for (WebElement filter : memoryFilters){
            if (filter.getText().toLowerCase().contains(memory.toLowerCase())){
                filter.click();
                break;
            }
        }

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("control-bar"), numOfResultsPriorFilter));
    }

    private void applySorting(String sorting){
        Select dropdown= new Select(sortingDropdown);
        dropdown.selectByVisibleText(sorting);
    }

    private void clickOnItem(int index) throws InterruptedException {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("grid-products")));
        WebElement productGrid= driver.findElement(By.id("grid-products"));

        // needed to insert sleep here since it was returning empty list
        Thread.sleep(4000);
        List<WebElement> items= productGrid.findElements(By.className("product-item-css"));
        WebElement selectedItem= items.get(index-1);
        WebElement selectedItemName= selectedItem.findElement(By.className("product-item-name"));
        selectedItemName.click();

    }

    public boolean pricesInDescending() throws InterruptedException {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("grid-products")));
        WebElement productGrid= driver.findElement(By.id("grid-products"));

        // needed to insert sleep here since it was returning empty list...???
        Thread.sleep(4000);
        List<WebElement> items= productGrid.findElements(By.className("product-item-css"));

        List<Integer> list= new ArrayList<Integer>();

        for (WebElement item : items){
            WebElement price= item.findElement(By.className("ppra_price"));
            int priceInt= Integer.parseInt(price.getText().replace(".", ""));
            list.add(priceInt);
        }

        return Comparators.isInOrder(list, Comparator.<Integer> naturalOrder().reversed());
    }


    public void applyFiltersAndSorting(String category, String processor, String memory, String sorting) throws InterruptedException {
        selectCategory(category);
        selectProcessor(processor);
        selectMemory(memory);
        applySorting(sorting);
    }

    public void navigateToArticlePage(String category, String processor, String memory, String sorting, int index) throws InterruptedException {
        selectCategory(category);
        selectProcessor(processor);
        selectMemory(memory);
        applySorting(sorting);
        clickOnItem(index);
    }

}
