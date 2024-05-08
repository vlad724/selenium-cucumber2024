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

public class ActionChains3DragDropTest extends WebDriverHelper{
    WebDriver driver = null;

    WebDriverFactory webDriverFactory =  new WebDriverFactory();

    ActionChainsPageObjects actionChainsPageObjects =  new ActionChainsPageObjects();

    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://testpages.eviltester.com/styled/drag-drop-javascript.html" );

    }

    @Test
    public void Action1Test(){

        /*
       Action seriesOfActions = builder
                .moveToElement(txtUsername)
                .click()
                .keyDown(txtUsername, Keys.SHIFT)
                .sendKeys(txtUsername, "hello")
                .keyUp(txtUsername, Keys.SHIFT)
                .doubleClick(txtUsername)
                .contextClick()
                .build();

                seriesOfActions.perform() ;
                */
        Actions builder = new Actions(driver);

//buildear acciones
        Action seriesOfActions = dragAndDropToElement(driver,actionChainsPageObjects.draggable2Loc,actionChainsPageObjects.droppable1Loc);// metodo creado en WebDriverHelper para simplificar

//ejecutar acciones
        seriesOfActions.perform();


//cada builder debe ir con su perform() en orden
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}