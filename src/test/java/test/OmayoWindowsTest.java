package test;

import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObject.OmayoPageObjects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OmayoWindowsTest extends WebDriverHelper {

    WebDriver driver = null;

    WebDriverFactory webDriverFactory = new WebDriverFactory();

    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();


    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://omayo.blogspot.com/" );
        windowsHandle.put("principal", driver.getWindowHandle());

    }

    @Test
    public void windows1Test(){

        WebElement seleniumTutorialElem = getElement(driver, omayoPageObjects.seleniumTutorialLinkLoc);
        WebElement newPopUElem = getElement(driver, omayoPageObjects.newPopUpLoc);

        if(seleniumTutorialElem != null){

            seleniumTutorialElem.click();
            getWindowsHandle(driver, "SeleniumTutorial");

        }

        getWindowsHandle(driver, "principal");

        if(newPopUElem != null){

            newPopUElem.click();
            getWindowsHandle(driver, "NewPopUp");

        }

        getWindowsHandle(driver, "principal");

       /* //ventana actual
        driver.getWindowHandle();

        //ventanas abiertas
        driver.getWindowHandles();


        driver.switchTo().window("");*/


    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
