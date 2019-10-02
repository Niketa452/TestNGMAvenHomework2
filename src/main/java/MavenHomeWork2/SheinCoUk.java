package MavenHomeWork2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SheinCoUk extends Utils {
    LoadProperties props = new LoadProperties();
    SoftAssert s_assert=new SoftAssert();

    @BeforeMethod
    public void setup() {
        launchingChromeDriver();
        driver.get("https://www.shein.co.uk/");

         }
    @Test
    public void userShouldBeAbleToAddKidsProductsToCart(){
        s_assert= new SoftAssert();
        clickElement(By.xpath("//a[@title='Kids']"));//click on kids tab
        pointcursorToWebelement(By.xpath("//span[contains(text(),'Shoes & Accessories')]"));//mouse hover on shoes and accessories
        clickElement(By.xpath("//a[contains(text(),'Older Girls 7-12 yrs')]"));//click on older girls 7-12
        clickElement(By.xpath("//img[@alt='Kids Deer Charm Crossbody Bag']"));//click on a bag image
        waitForElementVisible(By.xpath("//a[@id='710549']"), 2000);
        clickElement(By.xpath("//a[@id='710549']"));//clicking and selecting brown colour
        clickElement(By.xpath("//button[contains(text(), 'Add To Bag')]"));//click on add to cart
        clickElement(By.xpath("//img[@src='//img.ltwebstatic.com/images2_pi/2019/09/16/1568618964403320620_thumbnail_405x552.jpg']"));//
        clickElement(By.xpath("//span[@class='opt-qty-span iconfont icon-add']"));//click on + button to add quantity
        clickElement(By.xpath("//button[contains(text(), 'Add To Bag')]"));//click on add to cart
        String bag_name=getTextFromContent(By.xpath("//h1[@class='name']"));//getting text of the bag product
        String hair_ties=getTextFromContent(By.xpath("//h1[@class='name']"));//getting text from hairties added
        String expected =bag_name.concat(hair_ties);//concating
        System.out.println(expected);
        String hairties_cart=getTextFromContent(By.xpath("//a[contains(text(),'Girls Simple Velvet Hair Tie 4pcs')]"));//getting name from the cart
        String bag_cart=getTextFromContent(By.xpath("//a[contains(text(),'Kids Deer Charm Crossbody Bag')]"));//getting name from the cart
        String actual=bag_cart.concat(hairties_cart);//concating
        System.out.println(actual);
        s_assert.assertEquals(actual, expected);//soft assert so that if the test fails the rest of the test would be executed
        s_assert.assertAll();

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

@AfterMethod
    public void teardown(){
        driver.quit();
}



}
