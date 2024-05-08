package Configuration;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class WebDriverHelper {
    //final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5); darle segundos de espera a que encuentre elemento
    final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5);
    public HashMap<String, String> windowsHandle = new HashMap<>();



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

    public Alert isAlertPresent(WebDriver driver){
        Alert simpleAlert = null;
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            wait.until(ExpectedConditions.alertIsPresent());
            simpleAlert = driver.switchTo().alert();
            System.out.println("Alert is present");

        }catch(Exception e){
            System.out.println("Alert is not present");
        }
        return simpleAlert;
    }

    public void getWindowsHandle(WebDriver driver, String windowsName){
        boolean alreadyExist;
        sleep(10);
        if (windowsHandle.containsKey(windowsName)) {
            driver.switchTo().window(windowsHandle.get(windowsName));
            System.out.println(
                    String.format(
                            "I go to Windows: %s with value: %s ",
                            windowsName, windowsHandle.get(windowsName)));
        } else {
            for (String winHandle : driver.getWindowHandles()) {
                for (String entry : windowsHandle.keySet()) {
                    String value = windowsHandle.get(entry.trim());
                    alreadyExist = StringUtils.equalsIgnoreCase(value, winHandle);
                    if (!alreadyExist) {
                        windowsHandle.put(windowsName, winHandle);
                        System.out.println(
                                "The New window"
                                        + windowsName
                                        + "is saved in scenario with value"
                                        + windowsHandle.get(windowsName));
                        driver.switchTo().window(winHandle);
                        break;
                    }
                }
            }
        }
    }
    //)
   public void sleep(int seconds){
        try {
            Thread.sleep(1000*seconds);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
   }

   public Actions createActionBuilder(WebDriver driver){
        return new Actions(driver);
   }
   public Action moveToElement(WebDriver driver, By loc){
//buildear acciones
       return createActionBuilder(driver)
               .moveToElement(getElement(driver, loc))
               .build();
   }

    public Action moveToElementAndClick(WebDriver driver, By loc){
//buildear acciones
        return createActionBuilder(driver)
                .moveToElement(getElement(driver, loc))
                .click(getElement(driver, loc))
                .build();
    }

    public Action dragAndDropToElement(WebDriver driver, By sourceLoc, By targetLoc){
//buildear acciones
        return createActionBuilder(driver)
                .dragAndDrop(getElement(driver,sourceLoc),getElement(driver,targetLoc))
                .build();
    }
}
