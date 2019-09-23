package MavenHomeWork2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Utils extends BasePAge {


    public static void enterText(By by, String text) {//to enter text at given location
        driver.findElement(by).sendKeys(text);

    }

    public static String getTextFromContent(By by) {//to get text from location
        return driver.findElement(by).getText();

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

    public static void waitForElementToGetInvisible(By by, int time) {//wait for an element to disappear
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }

    public static String randomdate() {//to generate unique number everytime it is called
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());


    }

    public static void pointcursorToWebelement(By by) {//mouse hover action
        Actions actions = new Actions(driver);//creating instance of action class for mouse hover.
        WebElement menuList = driver.findElement(by);
        actions.moveToElement(menuList).perform();

    }

    public static void launchingChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Webdriver\\chromedriver.exe");//calling the chromedriver path
        driver = new ChromeDriver();//creating chrome driver object
        driver.manage().window().fullscreen();//to maximise the web page.
        driver.manage().timeouts().implicitlyWait(30, SECONDS);//waiting time before opening teh website
    }

    public static void launchWithHeadlessBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);

    }

    public static void selectByVisibleTesxt(By by, String text) {//handling dropdown by visible text
        Select selectmonth = new Select(driver.findElement(by));
        selectmonth.selectByValue(text);

    }

    public static void selectByValue(By by, String text) {//handling dropdown by visible text
        Select selectval = new Select(driver.findElement(by));
        selectval.selectByValue(text);

    }

    public static void selectByIndex(By by, int indexnum) {//handling dropdown by visible text
        Select selectindx = new Select(driver.findElement(by));
        selectindx.selectByIndex(indexnum);
    }

    public static void clearTextFromInputBoxorArea(By by) {//to clear text
        driver.findElement(by).clear();
    }

    public static void clearAndEnterText(By by, String text) {//to clear and enter text
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);

    }

    public static boolean isWebElementVisible(By by) {//to know whether webelement is visible or not
        boolean result = driver.findElement(by).isDisplayed();
        return result;
    }

    public static boolean isWebElementSelected(By by) {//to know whether webelement is selected or not
        boolean result = driver.findElement(by).isSelected();
        return result;
    }

    public static boolean isWebElementEnabled(By by) {//to know whether webelement is enabled or not
        boolean result = driver.findElement(by).isEnabled();
        return result;
    }

    public static String getselectedValueFromDropdownList(By by, String valueoftext) {//to get selected value from the dropdown list
        String selected_menu = null;
        Select selectobj = new Select(driver.findElement(by));//approaching the dropdown list
        selectobj.selectByValue(valueoftext);
        WebElement value_menu = selectobj.getFirstSelectedOption();
        selected_menu = value_menu.getText();
        return selected_menu;

    }

    public static String getselectedTextFromDropdownList(By by, String visibletext) {//to get selected text from the dropdown list
        String selected_menu = null;
        Select selectobj = new Select(driver.findElement(by));//approaching the dropdown list
        selectobj.selectByVisibleText(visibletext);
        WebElement text = selectobj.getFirstSelectedOption();
        selected_menu = text.getText();
        return selected_menu;
    }

    public static int getselectedIndexFromDropdownList(By by, int index_num) {
        int selected_index = 0;
        Select selectobj = new Select(driver.findElement(by));//approaching the dropdown list
        selectobj.selectByIndex(index_num);
        WebElement num = selectobj.getFirstSelectedOption();
        selected_index = Integer.parseInt(num.getText());
        return selected_index;
    }

    public static String getAttributefromWebelement(By by, String value) {//to get attribute from webelement
        WebElement element = driver.findElement(by);
        String getAttribute = element.getAttribute(value);//value can be name, id, class or aria lable
        return getAttribute;
    }

    public static String getCssPropertyofWebelement(By by, String text) {//to get cssproperty
        WebElement element = driver.findElement(by);
        String cssProperty = element.getCssValue(text);
        return cssProperty;
    }


    public static String verifyandgetTitle() {//to verify and get title
        String title = driver.getTitle();
        return title;
    }

    public static void toNavigatetoanotherURL(String url) {//to navigate to other url
        driver.navigate().to(url);
    }

    public static void toNavigateBackwards() {//to navigate backwards
        driver.navigate().back();
    }

    public static void toNavigateForward() {//to navigate forward
        driver.navigate().forward();
    }

    public static void toRefreshWebpage() {//to refresh
        driver.navigate().refresh();
    }

    public static String bootstrapdropdownlist(By by, String text) {//to select from dropdown list using bootstrop
        String actual = null;
        List<WebElement> ele = driver.findElements(by);
        for (WebElement elements : ele) {
            actual = (elements.getText());
            if (actual.equals(text)) {
                elements.click();
                break;

            }
        }
        return actual;
    }

    public static void takeScreenshot(WebDriver webdriver, String fileWithPath) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);//convert webdriver object to TakeScreenshot
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);//call getScreenshot method to create image file
        File destFile = new File(fileWithPath);//move image file to new destination
        FileUtils.copyFile(scrFile, destFile);//copy file to destinaiton.

    }
}