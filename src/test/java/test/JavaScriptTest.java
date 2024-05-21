package test;

import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObject.ActionChainsPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScriptTest extends WebDriverHelper{
    WebDriver driver = null;

    WebDriverFactory webDriverFactory =  new WebDriverFactory();

    ActionChainsPageObjects actionChainsPageObjects =  new ActionChainsPageObjects();

    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://www.geeksforgeeks.org/" );


    }

    @Test
    public void jsTest(){

        //getElement(driver, actionChainsPageObjects.djangoCourse).click();

        jsClick(driver, actionChainsPageObjects.djangoCourseloc);
        waitPageCompletelyLoaded(driver);
        scrollToElement(driver, actionChainsPageObjects.footerLoc);



    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}