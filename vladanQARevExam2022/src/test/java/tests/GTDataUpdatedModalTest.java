package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

public class GTDataUpdatedModalTest extends BaseTest {

    // tests that
    @Test
    public void updateModalDisplayedWithCorrectMessageTest() throws InterruptedException {

        String url= "https://gigatron.rs";
        String email= "gt211123@gmail.com";
        String password= "proba211123";
        String successMessage= "Podaci su uspešno sačuvani!";

        GTHomePage homePage= new GTHomePage(driver);
        homePage.navigateToLoginPage(url);

        GTLogInPage logInPage= new GTLogInPage(driver);
        logInPage.enterCredentialsAndSubmit(email, password);

        GTProfileMainPage profileMainPage= new GTProfileMainPage(driver);
        profileMainPage.navigateToPersonalDataPage();

        GTPersonalDataPage personalDataPage= new GTPersonalDataPage(driver);
        personalDataPage.updatePhoneAndSave();

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-modal")));
        WebElement infoModal= driver.findElement(By.className("swal2-modal"));


        Assert.assertTrue("Save-successfull info modal is not displayed",
                infoModal.isDisplayed());

        Assert.assertTrue("Incorrect message on the info modal",
                infoModal.getText().toLowerCase().contains(successMessage.toLowerCase()));



        Thread.sleep(5000);

    }


}
