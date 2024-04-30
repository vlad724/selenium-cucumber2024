package PageObject;

import org.openqa.selenium.By;

public class OrangeHRMPageObjects {

    public By logoImgLoc = new By.ByCssSelector("img[src*='/web/images/ohrm_branding.png']");
    public By userNameLoc = new By.ByCssSelector("input[name='username']");
    public By passwordLoc = new By.ByCssSelector("input[placeholder='Password']");
    public By loginBtnLoc =  By.xpath("//button[@type='submit']");
    public By userBulletLoc =  By.cssSelector("img[alt='profile picture']");

}
