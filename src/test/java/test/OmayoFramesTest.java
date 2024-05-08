package test;

import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObject.OmayoPageObjects;
import Pages.OmayoPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OmayoFramesTest extends WebDriverHelper {

    WebDriver driver = null;

    WebDriverFactory webDriverFactory = new WebDriverFactory();

    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();

    OmayoPage omayoPage = new OmayoPage();


    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://omayo.blogspot.com/" );
        windowsHandle.put("principal", driver.getWindowHandle());

    }

    @Test
    public void frames1Test(){
        omayoPage.iFrameMenu(driver);// metodo creado en OmayoPage para resumir el frame1

        driver.switchTo().parentFrame();//nos devolvemos al parentframe o al inicio de la pagina

        omayoPage.clickAlertAceept(driver);// metodo para hacer clcick en alert

        omayoPage.IframeMenu2(driver);// metodo creado en OmayoPage para resumir el frame1
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
