package test;

import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObject.OmayoPageObjects;
import org.checkerframework.checker.units.qual.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class OmayoTest extends WebDriverHelper {

    WebDriver driver = null;
    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();

    WebDriverFactory webDriverFactory = new WebDriverFactory();


    @BeforeTest
    public void setUp(){

        driver = webDriverFactory.createWebDriver("CHROME","https://omayo.blogspot.com/" );

    }

    @Test
    public void selenium1TextBoxTest(){



        // con el metodo getElement() validamos que el elemento se encuentre y ese metodo debe ser creado
        WebElement txtFNameEle = getElement(driver,omayoPageObjects.txtFNameLoc);
        WebElement txtFIdEle = getElement(driver, omayoPageObjects.txtFIdLoc);
        WebElement txtFxpathEle = getElement(driver, omayoPageObjects.txtFxpathLoc);

        if(txtFNameEle!=null && txtFNameEle!=null && txtFNameEle!=null){

            txtFNameEle.clear();
            txtFNameEle.sendKeys("Hola FName");

            txtFIdEle.clear();
            txtFIdEle.sendKeys("Hola FIdLoc");

            txtFxpathEle.clear();
            txtFxpathEle.sendKeys("Hola textarea2");
        }else{
            throw new SkipException("Skipping the test case");
        }


    }

    @Test
    public void selenium2DropDownTest(){



        //WEB ELEMENT
        WebElement dropElm = getElement(driver, omayoPageObjects.drop1Loc);
        Select dropdown = new Select(dropElm);

        WebElement multiselectElm = getElement(driver, omayoPageObjects.multiselect1Loc);
        Select multiselect = new Select(multiselectElm);

        //buena practica para capturar los errores cuando no encuentra elemento de un dropdown
        try{
            //SELECT by visible text
            dropdown.selectByVisibleText("doc 1");

            //SELECT by value (ver en el DOM ej jkl = doc 3)
            dropdown.selectByValue("jkl");

            //MULTI SELECT by value
            multiselect.selectByValue("audix");
            multiselect.selectByValue("volvox");

            //MULTI SELECT by value
            multiselect.selectByVisibleText("Hyundai");

        }catch(NoSuchElementException | TimeoutException e){

            dropdown.selectByIndex(0);

            multiselect.selectByIndex(0);
        }

    }

    @Test
    public void selenium3DropDownTest(){



        //WEB ELEMENT
        WebElement dropElm = getElement(driver, omayoPageObjects.drop1Loc);
        Select dropdown = new Select(dropElm);

        WebElement multiselectElm = getElement(driver, omayoPageObjects.multiselect1Loc);
        Select multiselect = new Select(multiselectElm);

        //buena practica para capturar los errores cuando no encuentra elemento de un dropdown
        List<WebElement> multiSelectListOpt = multiselect.getOptions();
        Optional<WebElement> matchingOption = multiSelectListOpt.stream()
                .filter(option -> option.getText().equals("Audi"))
                .findFirst();
        matchingOption.ifPresent(elm -> elm.click());

        List<WebElement> dropOpt = dropdown.getOptions();
        Optional<WebElement> dropMatchingOption = dropOpt.stream()
                .filter(option -> option.getText().equals("doc 4"))
                .findFirst();
        dropMatchingOption.ifPresent(elm -> elm.click());
    }


    @Test
    public void selenium4XpathTest(){


        //WEB element
        List<WebElement> table1Elem = getElements(driver, omayoPageObjects.table1loc);
        WebElement table1PuneElm = getElement(driver, omayoPageObjects.table1PuneLoc);
        WebElement btnSameNameLoginElm = getElement(driver,omayoPageObjects.btnSameNameLoginLoc);
        List<WebElement> btnSameNameElem = getElements(driver, omayoPageObjects.btnSameNameLoc);

        //Encontrar un texto dentro de un WebElement List
        for(WebElement elem : table1Elem){
            if(elem.getText().equals("Bangalore")){
                System.out.println(elem.getText());
                break;
            }
        }

        //get Text
        System.out.println(table1PuneElm.getText());

        //CLick on element
        btnSameNameLoginElm.click();

        //Encontrar un texto dentro de un WebElement List
        for(WebElement btn : btnSameNameElem){
            if(btn.getText().equals("Register")){
                btn.click();
                break;
            }
        }

    }



    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    private Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }


}
