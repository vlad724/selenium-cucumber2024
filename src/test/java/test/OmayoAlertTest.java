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

public class OmayoAlertTest extends WebDriverHelper {

    WebDriver driver = null;

    WebDriverFactory webDriverFactory = new WebDriverFactory();

    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();


    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://omayo.blogspot.com/" );
        windowsHandle.put("principal", driver.getWindowHandle());

    }

    @Test
    public void alert1Test(){

        WebElement alert1Elem = getElement(driver, omayoPageObjects.btnAlert1);

        if (alert1Elem != null){
            alert1Elem.click();

            //isAlertPresent es un metodo creado en la clase WebDriverHelper parta verificar que la alerta este presente
            Alert alert1 =  isAlertPresent(driver);


            if(alert1 != null){
                alert1.accept();
            }

        }
    }
    @Test
    public void promt1Test(){

        WebElement prompt1Elem = getElement(driver, omayoPageObjects.btnPrompt1);

        if (prompt1Elem != null){
            prompt1Elem.click();

            //isAlertPresent es un metodo creado en la clase WebDriverHelper parta verificar que la alerta este presente
            Alert prompt1 =  isAlertPresent(driver);


            if(prompt1 != null){
                System.out.println(prompt1.getText());
                //No funciona el test planteado
                prompt1.sendKeys("Hola que tal");
                prompt1.accept();
            }

        }
    }
    @Test
    public void alert2YesNoTest(){

        WebElement confirm1Elem = getElement(driver, omayoPageObjects.btnConfirm1);

        if (confirm1Elem != null){
            confirm1Elem.click();

            //isAlertPresent es un metodo creado en la clase WebDriverHelper parta verificar que la alerta este presente
            Alert confirm1 =  isAlertPresent(driver);


            if(confirm1 != null){
                confirm1.dismiss();
            }

        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
