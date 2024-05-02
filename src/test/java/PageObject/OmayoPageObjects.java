package PageObject;

import org.openqa.selenium.By;

public class OmayoPageObjects {
    public By txtFNameLoc = By.name("fname");
    public By txtFIdLoc =  By.id("ta1");
    public By txtFxpathLoc = By.xpath("//textarea[contains(text(),'The cat was playing in the garden.')]");
    public By multiselect1Loc = By.id("multiselect1");
    public By drop1Loc = By.id("drop1");
    public By table1loc = new By.ByCssSelector("table[id='table1'] td");
    public By table1PuneLoc = By.xpath("//table[@id='table1']//td[contains(.,'Pune')]");
    public By btnSameNameLoginLoc = By.xpath("//button[contains(text(),'Login')]");
    public By btnSameNameLoc = By.xpath("//button[@name='samename']");
    public By btnAlert1 = By.id("alert1");
    public By btnConfirm1 = By.id("confirm");
    public By btnPrompt1 = By.id("prompt");
    public By seleniumTutorialLinkLoc = By.id("selenium143");
    public By newPopUpLoc = By.linkText("Open a popup window");
    public By questionLoc = By.linkText("What is Salenium?");

}
