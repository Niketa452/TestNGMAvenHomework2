package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils extends BasePAge {

    public static void launchingChromeDriver() {//to launch the webdriver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Webdriver\\chromedriver.exe");//calling the chromedriver path
        driver = new ChromeDriver();//creating chrome driver object
        driver.manage().window().fullscreen();//to maximise the web page.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//waiting time before opening teh website
    }

    public static void enterText(By by, String text) {//to enter text at given location
        driver.findElement(by).sendKeys(text);

    }

    public static String getTextFromContent(By by) {//to get text from location
        return driver.findElement(by).getText();//will return the text acquired

    }

    public static void clickElement(By by) {//to click on given web element
        driver.findElement(by).click();
    }

    public static void waitUntilEleemtLoadAndIsClickable(By by, long time) {//wait for element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementVisible(By by, long time) {//wait for element to be visible
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForAlertPresent(long time) {//wait for alert to display
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static String randomdate() {//to generate unique number everytime it is called
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }

    public static void pointcursorToWebelement(By by) {//for mouse hover action
        Actions actions = new Actions(driver);//creating instance of action class for mouse hover.
        WebElement menuList = driver.findElement(by);//cursor approaching the menulist through locator
        actions.moveToElement(menuList).perform();//performing hover action
    }

    public static void selectByVisibleTesxt(By by, String text) {//handling dropdown by visible text
        Select selecttext = new Select(driver.findElement(by));
        selecttext.selectByValue(text);

    }

    public static void selectByValue(By by, String text) {//handling dropdown by value of the text given in the DOM
        Select selectvalue = new Select(driver.findElement(by));
        selectvalue.selectByValue(text);

    }

    public static void selectByIndex(By by, int indexnum) {//handling dropdown by index given in the DOM
        Select selectindex = new Select(driver.findElement(by));
        selectindex.selectByIndex(indexnum);
    }

    public static void clearTextFromInputBoxorArea(By by) {//to clear text from the input box or area
        driver.findElement(by).clear();
    }

    public static void clearAndEnterText(By by, String text) {//to first clear text then enter new text
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    public static void isWebElementVisible(By by) {//to check whether a webelement is present or not
        boolean result = driver.findElement(by).isDisplayed();
        System.out.println("Result is = " + result);
    }

    public static void isWebElementSelected (By by) {// to check whether a webelement is selected or not
        boolean result = driver.findElement(by).isSelected();
        System.out.println("Result is =" + result);
    }

    public static void isWebElementEnabled(By by) {//to check whether a webelement is enabled after clicking or not
        boolean result = driver.findElement(by).isEnabled();
        System.out.println("Result is =" + result);
    }
    public static void getselectedValueFromDropdownList (By by, String valueoftext){//to get selected value from the dropdown list
        Select selectobj = new Select (driver.findElement(by));//approaching the dropdown list and storing in a variable
        selectobj.selectByValue(valueoftext);//obtaining value by selectbyvalue method
        WebElement value_menu = selectobj.getFirstSelectedOption();//storing the value by getfirstselectedoption method in a variable
        String selected_menu = value_menu.getText();//getting the text
        System.out.println("Value selected from the dropdown is : " + selected_menu);//printing the text
    }
    public static void getselectedTextFromDropdownList (By by, String visibletext) {//to get visibletext of the selected option from the dropdown list
        Select selectobj = new Select(driver.findElement(by));//approaching the dropdown list
        selectobj.selectByVisibleText(visibletext);//obtaining text by selectbyvisibletext method
        WebElement text = selectobj.getFirstSelectedOption();//storing the value by getfirstselectedoption method in a variable
        String selected_menu = text.getText();//getting the text
        System.out.println("Text selected from the dropdown is : " + selected_menu);//printing the text
    }
    public static void getselectedIndexFromDropdownList (By by, int index_num) {//to get index number of the selected option from the dropdown list
        Select selectobj = new Select(driver.findElement(by));//approaching the dropdown list
        selectobj.selectByIndex(index_num);//obtaining number by selectbyindex method
        WebElement num = selectobj.getFirstSelectedOption();//storing the number by getfirstselectedoption method in a variable
        String selected_menu = num.getText();//getting the index number
        System.out.println("Index selected from the dropdown is : " + selected_menu);//printing the number
    }

    public static void getAttributefromWebelement(By by, String value){//to get attribute of a webelement
        WebElement element = driver.findElement(by);//storing the locator of the webelement in a variable
        System.out.println(element.getAttribute(value));//value can be name, id, class or aria lable, printing the value
    }
    public static void getCssPropertyofWebelement (By by, String text){//to get the CSS property of the webelement
        WebElement element = driver.findElement(by);//storing the locator of the webelement in a variable
        System.out.println(element.getCssValue(text));//printing the attribute.
    }

}
