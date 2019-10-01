package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static MavenHomeWork2.Utils.clickElement;
import static MavenHomeWork2.Utils.launchingChromeDriver;


public class NopCommerce extends Utils {
    LoadProperties props = new LoadProperties();
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setup() {
        launchingChromeDriver();//method called to launch the browser
        driver.get(props.getProperty("url"));//web page url given

    }

    @Test
    public void userShouldBeAbleToRegisterSuccessfully() {

        //to click on register button for registration
        clickElement(By.xpath("//a[@class='ico-register']"));

        //registration form compulsory fields to be filled, Enter name
        enterText(By.id("FirstName"), props.getProperty("FirstName"));

        //Enter surname
        enterText(By.xpath("//input[@name='LastName']"), props.getProperty("LastName"));

        //Selecting date from the dropdown list
        selectByVisibleTesxt(By.xpath("//select[@name='DateOfBirthDay']"), "5");

        //Selecting month from the dropdown list
        selectByValue(By.xpath("//select[@name='DateOfBirthMonth']"), "8");

        //Selecting year from the dropdown list
        selectByIndex(By.xpath("//select[@name='DateOfBirthYear']"), 90);

        //Enter email
        enterText(By.name("Email"), props.getProperty("Emailstart") + randomdate() + props.getProperty("Emailend"));

        //Enter password
        enterText(By.id("Password"), props.getProperty("Password"));

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
        String actualMessage = getTextFromContent(By.xpath("//div[@class='result']"));

        String expectedConfirmationMessage = "Your message has been sent.";//storing expected message in a string variable

        System.out.println("Acutal confirmation message is : " + actualMessage);//

        Assert.assertEquals(actualMessage, expectedConfirmationMessage);//comparing actual result with expected.
    }

    @Test
    public void userShouldBeAbleToNavigateToCameraAndPhotoPage() {

        //calling Aaction class method for mouse hover and making cursor perform
        pointcursorToWebelement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));

        //selecting and clicking camera and photo subcategory.
        clickElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));

        //getting and storing actual display message.
        String actual_msg = getTextFromContent(By.xpath("//h1"));

        String expected_msg = "Camera & photo";

        System.out.println("Actual tittle displayed is : " + actual_msg);

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
        String price = getTextFromContent(By.xpath("//span[@class='price actual-price']"));

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
        String pageTitle = getTextFromContent(By.xpath("//h1[contains(text(),'Jewelry')]"));


        String actual_title = "Jewelry";

        System.out.println("you are navigated to : " + actual_title + " page.");

        Assert.assertEquals(pageTitle, actual_title);
    }

    @Test
    public void userShouldBeAbleToAddTheProductsToTheShoppingCart() {

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
        String qty = getTextFromContent(By.xpath("//span[@class='cart-qty']"));


        System.out.println("Actual quantity ordered : " + qty);
        String expected_qty = "(2)";
        Assert.assertEquals(qty, expected_qty);//validating that shopping cart shows exact number of products shopped
    }

    @Test
    public void counthowmanyaddtocartbuttonsonthehomepage() {
        int ispresent = 0;
        int notpresent = 0;
        List<WebElement> cart = driver.findElements(By.xpath("//div[@class='item-box']"));

        for (WebElement el : cart) {
            if (el.getAttribute("outerHTML").contains("Add to cart")) {
                ispresent++;
                el.getText();
                System.out.println("'Add to cart' button is present in the following item: " + el.getText());
            } else {

                notpresent++;
                System.out.println("'Add to cart' button is not present in the following item: " + el.getText());
            }
        }
        Assert.assertEquals(cart.size(), ispresent);
    }

    @Test
    public void userShouldBeAbleToCompareProducts() throws InterruptedException {
        int i = 0;
        ((JavascriptExecutor) driver).executeScript("scroll(0, 1200)");
        clickElement(By.xpath("//div[@class='page-body']//div[1]/div/div[2]/div[3]/div[2]/input[2]"));//click on "Add to Compare list" button
        waitForElementVisible(By.xpath("//div[@class='bar-notification success']"), 2000);//wait for green line to appear
        String actual = getTextFromContent(By.xpath("//div[@class='bar-notification success']"));//get confirmation message text
        String expected = "The product has been added to your product comparison";//expected confirmation message text
        softAssert.assertEquals(actual, expected);//soft assert
        clickElement(By.xpath("//div[@class='item-grid']//div[2]//div[1]//div[2]//div[3]//div[2]//input[2]"));//click on second product "Add to Compare list" button
        Thread.sleep(2000);//waiting time after clicking the second product.
        String actual_2 = getTextFromContent(By.xpath("//div[@class='bar-notification success']"));//get confrimation message text
        String expected_2 = "The product has been added to your product comparison";//exptected confirmation message text
        softAssert.assertEquals(actual_2, expected_2);//soft assert that second product has been added to the compare product list
        waitForElementVisible(By.linkText("Compare products list"), 2000);
        ((JavascriptExecutor) driver).executeScript("scroll(0,1500)");//scroll to the end of the page
        clickElement(By.linkText("Compare products list"));//click on compare list
        String macbook = getTextFromContent(By.xpath("//a[contains(text(),'Apple MacBook Pro 13-inch')]"));//storing in a string
        String buildyourcomputer = getTextFromContent(By.xpath("//a[contains(text(),'Build your own computer')]"));//storing in a string
        List<WebElement> compare_product = driver.findElements(By.xpath("//tr[@class='product-name']"));//storing the table content in a list
        for (WebElement element : compare_product) {//iterating the list
            String expected_final = element.getText();//printing the list
            if (expected_final.contains(macbook)) {
                i++;
                if (expected_final.equals(buildyourcomputer)) {
                    i++;
                }
            }
        }
        softAssert.assertEquals(compare_product.size(), i);//asserting products are present on the compare list page

        clickElement(By.xpath("//a[@class='clear-list']"));//click on clear list button
        if (compare_product.size() == 0) {
            System.out.println("You have no items to compare.");

        }
        softAssert.assertEquals(compare_product.size(), i);//asserting that the compare list is clear


    }

    @Test
    public void userShouldBeAbleToAddCommentsAndTheSameShouldBeDisplayedAtTheBottomOfTheCoomentsList() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 1500)");
        clickElement(By.xpath("//a[contains(text(),'nopCommerce new release!')]"));//click on nopCommerce new release
        enterText(By.xpath("//input[@id='AddNewComment_CommentTitle']"), props.getProperty("CommentTitle"));//entering text
        String comment = props.getProperty("CommentTitle");
        enterText(By.xpath("//textarea[@id='AddNewComment_CommentText']"), props.getProperty("CommentText"));//entering comment
        clickElement(By.xpath("//input[@name='add-comment']"));//click on new comment button
        String confirmation_message = getTextFromContent(By.xpath("//div[@class='result']"));//storing the message in a string
        System.out.println(confirmation_message);
        List<WebElement> comment_list = driver.findElements(By.xpath("//div[@class='comments']//div[2]//div[@class='comment-title']"));//fetching the list
        WebElement last_comment = comment_list.get(comment_list.size() - 1);//storing the last value of the list in a webelement
        String mylastcomment = last_comment.getText();
        System.out.println(comment);
        System.out.println(mylastcomment);
        softAssert.assertEquals(mylastcomment, comment);//asserting that the comment is at the bottom of the list

    }

    @Test
    public void validatingTheSearchFunctionality() {
        int num = 0;
        int none = 0;
        enterText(By.xpath("//input[@id='small-searchterms']"), props.getProperty("productName"));//entering text to search
        String search_product = props.getProperty("productName");//storing the search product name in a string
        clickElement(By.xpath("//input[@class='button-1 search-box-button']"));//click on search button
        List<WebElement> search_item = driver.findElements(By.xpath("//div[@class='search-results']//div[@class='details']"));//storing webelements in a list
        if (search_item.size() == 0) {
            System.out.println("Product not found");
        }
        for (WebElement element : search_item) {//looping the list items
            if (element.getText().contains(props.getProperty("productName"))) {//checking whether searched word is present in the product list or not
                System.out.println("Product found: " + element.getText());//printing text of the product
                System.out.println(num++);//if the word is present, number should increase by 1
            } else {
                System.out.println("Product not found");
                System.out.println(none++);
            }

        }
        softAssert.assertEquals(search_item.size(), num);//soft asserting that the products shown on the page match with the search function.
        softAssert.assertAll();
    }


    @AfterMethod
    public void teardown() {

        driver.quit();//close the browser
    }

}


