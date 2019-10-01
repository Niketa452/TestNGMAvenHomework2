package MavenHomeWork2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SheinCoUk extends Utils {
    LoadProperties props = new LoadProperties();

    @BeforeMethod
    public void setup() {
        launchingChromeDriver();
        driver.get("https://www.shein.co.uk/");

         }

@Test
    public void userShouldBeAbleToSeeWhatsNew(){
        pointcursorToWebelement(By.xpath("//span[contains(text(),\"What's New\")]"));//mouse hover on whats new
        clickElement(By.xpath("//a[contains(text(),'09-24-2019')]"));//click on a date
        String expdate = getAttributefromWebelement(By.xpath("//a[@data-daily='2019-09-24']"), "data-daily");//get value
        String temp = driver.getCurrentUrl();//get url
        String arr[]= temp.split("=");//split string to get date on the basis of = sign
        String actual_date= arr[4];//date is in array index 4.
        Assert.assertEquals(actual_date, expdate);//assertion

}





}
