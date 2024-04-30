package test;

import Configuration.WebDriverHelper;
import PageObject.OrangeHRMPageObjects;
import Pages.OranageHRMPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OrangeHRMLoginTest extends WebDriverHelper {

    WebDriver driver = null;

    OrangeHRMPageObjects orangeHRMPageObjects = new OrangeHRMPageObjects();
    OranageHRMPage orangeHRMPage = new OranageHRMPage();

    @BeforeTest
    public void setUp(){
        System.out.println("Creating session...");
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void seleniumLoginTest(){

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        orangeHRMPage.login(driver, "Admin", "admin123");

        WebElement userBulletElm = getElement(driver, orangeHRMPageObjects.userBulletLoc);
        Assert.assertTrue(userBulletElm!=null, "El login fue incorrecto");


    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


}
