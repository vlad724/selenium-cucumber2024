package test;

import Configuration.WebDriverHelper;
import PageObject.OrangeHRMPageObjects;
import Pages.OranageHRMPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OrangeHRMTest extends WebDriverHelper {
    WebDriver driver = null;
    OrangeHRMPageObjects orangeHRMPageObjects = new OrangeHRMPageObjects();
    OranageHRMPage oranageHRMPage = new OranageHRMPage();



    @BeforeTest
    public void setUp(){
        System.out.println("Creating session...");
        Map<String, Object> prefs = new HashMap<String, Object>();
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        //capabilities
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu"); //deshabilitar animaciones.
        //options.addArguments("--window-size=1400,800"); //para aplicar se debe comentar o desahabilitar driver.manage().window().maximize(); mas abajo
        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

        //preferencias experimentales
        prefs.put("download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);


        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    @Test
    public void seleniumTest(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        oranageHRMPage.login(driver, "Admin", "admin123");


        WebElement userBulletElm = getElement(driver, orangeHRMPageObjects.userBulletLoc);

        Assert.assertTrue(userBulletElm!=null,"No se entro al login");
    }


    @Test
    public void selenium2Test(){
          driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");


          By usernameLoc2 =  By.name("username");
          By passwordLoc2 =  By.name("password");


          WebElement logoImgElm = driver.findElement(orangeHRMPageObjects.logoImgLoc);
          WebElement userNameElm = driver.findElement(orangeHRMPageObjects.userNameLoc);
          WebElement passwordElm = driver.findElement(orangeHRMPageObjects.passwordLoc);
          WebElement loginBtnElm = driver.findElement(orangeHRMPageObjects.loginBtnLoc);


          WebElement usernameElm2 = driver.findElement(usernameLoc2);
          WebElement passwordElm2 = driver.findElement(passwordLoc2);



          String altText = logoImgElm.getAttribute("alt");

        Assert.assertTrue(logoImgElm!=null,"El logo no existe");
        Assert.assertTrue(userNameElm!=null,"El username no existe");
        Assert.assertTrue(passwordElm!=null,"La password no existe");
        Assert.assertTrue(loginBtnElm!=null,"El boton no existe");

        Assert.assertTrue(usernameElm2!=null,"El username2 no existe");
        Assert.assertTrue(passwordElm2!=null,"La password2 no existe");


        Assert.assertEquals(altText, "company-branding", "El alt no coincide");

        userNameElm.sendKeys("Admin");
        passwordElm.sendKeys("admin123");

        loginBtnElm.click();

        By userBulletLoc =  By.cssSelector("img[alt='profile picture']");

        WebElement userBulletElm = driver.findElement(userBulletLoc);

        Assert.assertTrue(userBulletElm!=null,"No se entro al login");


    }

    @AfterTest
    public void tearDown(){
       driver.quit();
    }
    private Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }
}
