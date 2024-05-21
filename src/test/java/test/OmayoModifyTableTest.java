package test;

import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObject.OmayoPageObjects;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OmayoModifyTableTest extends WebDriverHelper {

    WebDriver driver = null;
    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();

    WebDriverFactory webDriverFactory = new WebDriverFactory();


    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://omayo.blogspot.com/" );

    }

    @Test
    public void modifyAttributeTest(){


       WebElement table1elem = getElement(driver, omayoPageObjects.table1xLoc);
       setAttribute(driver, omayoPageObjects.table1xLoc, "border","50");
       setAttribute(driver, table1elem, "border","100");

    }



    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    private Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }


}
