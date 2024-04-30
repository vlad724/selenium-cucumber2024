package Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WebDriverHelper {
    //final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5); darle segundos de espera a que encuentre elemento
    final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5);

    //metodos para cuando un elemento no esta presente (
    public WebElement getElement(WebDriver driver, By loc){
        return isWebElementDisplayed(driver, loc)? driver.findElement(loc):null;

    }

    public List<WebElement> getElements(WebDriver driver, By loc) {
        return isWebElementDisplayed(driver, loc)? driver.findElements(loc):null;
    }

    public boolean isWebElementDisplayed(WebDriver driver, By element) {
        boolean isDisplayed;
        try{
            System.out.println(String.format("waiting element: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed=wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed() && wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed=false;
            System.out.println(String.valueOf(e));
        }
        System.out.println(String.format("%s visibility is: %s",element,isDisplayed));
        return isDisplayed;
    }
    //)
}
