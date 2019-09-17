package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static MavenHomeWork2.Utils.clickElement;
import static MavenHomeWork2.Utils.launchingChromeDriver;


public class NopCommerce extends Utils {
    LoadProperties props = new LoadProperties();

    @BeforeMethod
    public void setup(){
        launchingChromeDriver();//method called to launch the browser
        driver.get(props.getProperty("url"));//web page url given
    }
    @Test
    public void userShouldBeAbleToRegisterSuccessfully(){

        //to click on register button for registration
        clickElement(By.xpath("//a[@class='ico-register']"));

        //registration form compulsory fields to be filled, Enter name
        enterText (By.id("FirstName"), props.getProperty("FirstName"));

        //Enter surname
        enterText(By.xpath("//input[@name='LastName']"),props.getProperty("LastName"));

        //Selecting date from the dropdown list
        selectByVisibleTesxt(By.xpath("//select[@name='DateOfBirthDay']"), "5");

        //Selecting month from the dropdown list
        selectByValue(By.xpath("//select[@name='DateOfBirthMonth']"), "8");

        //Selecting year from the dropdown list
        selectByIndex(By.xpath("//select[@name='DateOfBirthYear']"), 90);

        //Enter email
        enterText(By.name("Email"),props.getProperty("Emailstart")+randomdate()+ props.getProperty("Emailend"));

        //Enter password
        enterText(By.id("Password"),props.getProperty("Password"));

        //Enter Confirm password
        enterText(By.xpath("//input[@name='ConfirmPassword']"), props.getProperty("Password"));

        //click on register button
        clickElement(By.xpath("//input[@type='submit' and @name='register-button']"));

        //storing value of actual message in a string variable
        String actualMessage = getTextFromContent(By.className("result"));

        //actual display message
        String expectedMessage = "Your registration completed";

        System.out.println("Actual message is : " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);//validating acutal with expected

    }
    @Test
    public void emailAProductToAFriend() {
        userShouldBeAbleToRegisterSuccessfully();//calling registeration method

        //returning back to the home page for purchasing
        clickElement(By.xpath("//img"));

        //click on computers category
        clickElement(By.linkText("Computers"));

        //click on notebook category
        clickElement(By.linkText("Notebooks"));

        //selecting preferred notebook
        clickElement(By.linkText("Apple MacBook Pro 13-inch"));

        //clicking on the email a friend button to refer the product
        clickElement(By.xpath("//input[@value='Email a friend']"));

        //Enter friend's email address
        enterText(By.className("friend-email"), props.getProperty("FriendEmail"));

        //message to a friend
        enterText(By.xpath("//textarea"), props.getProperty("MessageToAFriend"));

        //Click on send button
        clickElement(By.name("send-email"));

        //getting actual text from the webpage
        String actualMessage=getTextFromContent(By.xpath("//div[@class='result']"));

        String expectedConfirmationMessage = "Your message has been sent.";//storing expected message in a string variable

        System.out.println("Acutal confirmation message is : "+actualMessage);//

        Assert.assertEquals(actualMessage,expectedConfirmationMessage);//comparing actual result with expected.
    }

    @Test
    public void userShouldBeAbleToNavigateToCameraAndPhotoPage(){

        //calling Aaction class method for mouse hover and making cursor perform
        pointcursorToWebelement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));

        //selecting and clicking camera and photo subcategory.
        clickElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));

        //getting and storing actual display message.
        String actual_msg = getTextFromContent(By.xpath("//h1"));

        String expected_msg = "Camera & photo";

        System.out.println("Actual tittle displayed is : "+actual_msg);

        Assert.assertEquals(actual_msg, expected_msg);//validating actual message with the expected message
    }
    @Test
    public void userShouldBeAbleToFilterJewelleryByPrice() {
        String ans;//declaring a string variable

        //clicking on 'Jewellery' on the homepage.
        clickElement(By.xpath(" //ul[@class='top-menu notmobile']//a[contains(text(),'Jewelry')]"));

        //clicking on the filter attribute of range $700-$3000
        clickElement(By.cssSelector("a[href='//demo.nopcommerce.com/jewelry?price=700-3000']"));

        //getting and storing text according to the filter selected.
        String price =getTextFromContent(By.xpath("//span[@class='price actual-price']"));

        System.out.println(price);//printing the price
        price = price.substring(0, 6);//to eliminate junk characters and converting string to integer value.
        price = price.replaceAll("[^0-9]", "");
        int x = Integer.parseInt(price);
        if (x >= 700 && x <= 3000) {//checking the filter function
            ans = "PASSED";
            System.out.println("Your test has " + ans);
        } else {

            ans = "FAILED";
            System.out.println("Your test has " + ans);

        }
        //verifying user is navigated to jewllery page
        String pageTitle=getTextFromContent(By.xpath("//h1[contains(text(),'Jewelry')]"));


        String actual_title="Jewelry";

        System.out.println("you are navigated to : "+actual_title+ " page." );

        Assert.assertEquals(pageTitle,actual_title);
    }
    @Test
    public void userShouldBeAbleToAddTheProductsToTheShoppingCart(){

        //clicking on books link
        clickElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));

        //Clicking on a book
        clickElement(By.xpath("//img[@alt='Picture of Fahrenheit 451 by Ray Bradbury']"));

        //adding a book to the cart
        clickElement(By.cssSelector("#add-to-cart-button-37"));

        //clicking on books link
        clickElement(By.xpath("//span[contains(text(),'Books')]"));

        //selecting another book to add to cart
        clickElement(By.cssSelector("img[title$='Prejudice']"));

        //adding to cart
        clickElement(By.cssSelector("#add-to-cart-button-39"));

        //instructing browser to wait
        waitUntilEleemtLoadAndIsClickable(By.cssSelector("img[title$='Prejudice']"), 60);

        //clicking on shopping cart lable to view the products added
        clickElement(By.xpath("//span[@class='cart-label']"));

        //calling method of Aciton class to perfrom mouse hover function
        pointcursorToWebelement(By.xpath("//span[@class='cart-label']"));

        //storing acquired text in a variable
        String qty =getTextFromContent(By.xpath("//span[@class='cart-qty']"));


        System.out.println("Actual quantity ordered : " + qty);
        String expected_qty = "(2)";
        Assert.assertEquals(qty, expected_qty);//validating that shopping cart shows exact number of products shopped
    }
    @AfterMethod
    public void teardown(){

        driver.quit();//close the browser


    }

}
