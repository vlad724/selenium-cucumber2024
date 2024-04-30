package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    WebDriver driver = null;



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
          driver.get("https://omayo.blogspot.com/");

          driver.findElement(new By.ByCssSelector("textarea[id='ta1']")).sendKeys("Esto es una prueba");
    }

    @AfterTest
    public void tearDown(){
       driver.quit();
    }
    private Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }
}
