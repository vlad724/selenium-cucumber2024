package Pages;

import PageObject.OrangeHRMPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OranageHRMPage {
    OrangeHRMPageObjects orangeHRMPageObjects = new OrangeHRMPageObjects();
 public void login(WebDriver driver, String user, String pass){
     WebElement userNameElm= driver.findElement(orangeHRMPageObjects.userNameLoc);
     WebElement passwordElm = driver.findElement(orangeHRMPageObjects.passwordLoc);
     WebElement loginBtnElm = driver.findElement(orangeHRMPageObjects.loginBtnLoc);

     userNameElm.sendKeys(user);
     passwordElm.sendKeys(pass);

     loginBtnElm.click();
 }

}
