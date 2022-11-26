package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.GTHomePage;
import pages.GTSearchResultsPage;

public class GTSortingTest extends BaseTest {

    // tests that items are displayed with prices in descending order once descending
    // filter on price is applied (previously we filter by product and additional subcategories)
    @Test
    public void descendingSortingWorksCorrectlyTest() throws InterruptedException {

        String url= "https://gigatron.rs";
        String product= "Apple";
        String category= "Laptop";
        String processor= "M2";
        String memory= "24GB";
        String sorting= "Ceni Opadajuće";

        GTHomePage homePage= new GTHomePage(driver);
        homePage.mainProductSearch(url, product);

        GTSearchResultsPage searchPage= new GTSearchResultsPage(driver);
        searchPage.applyFiltersAndSorting(category, processor, memory, sorting);

        Assert.assertTrue("Filtered items are not displayed in descending order by price",
                searchPage.pricesInDescending());


        Thread.sleep(3000);

    }


}
