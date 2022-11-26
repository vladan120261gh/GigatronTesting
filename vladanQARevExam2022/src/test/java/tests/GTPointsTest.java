package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.GTArticlePage;
import pages.GTHomePage;
import pages.GTSearchResultsPage;

public class GTPointsTest extends BaseTest {

    // tests that items are displayed with prices in descending order once descending
    // filter on price is applied (previously we filter by product and additional subcategories)
    @Test
    public void pointsAreCalculatedCorrectlyTest() throws InterruptedException {

        String url= "https://gigatron.rs";
        String product= "Apple";
        String category= "Laptop";
        String processor= "M2";
        String memory= "24GB";
        String sorting= "Ceni Opadajuće";
        int articlePosition= 1;

        GTHomePage homePage= new GTHomePage(driver);
        homePage.mainProductSearch(url, product);

        GTSearchResultsPage searchPage= new GTSearchResultsPage(driver);
        searchPage.navigateToArticlePage(category, processor, memory, sorting, articlePosition);

        GTArticlePage articlePage= new GTArticlePage(driver);

        Assert.assertTrue("Gigatron points are not correctly calculated",
                articlePage.pointsCorrectlyCalculated());


        Thread.sleep(3000);

    }


}
