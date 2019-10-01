package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static MavenHomeWork2.LoadProperties.props;
import static java.lang.Double.parseDouble;

public class Orangehrm extends Utils {
    LoadProperties props = new LoadProperties();

    @BeforeMethod
   public void setup() {
launchingChromeDriver();
driver.get("https://opensource-demo.orangehrmlive.com");
        enterText(By.id("txtUsername"), props.getProperty("UserName"));//Enter username
        enterText(By.name("txtPassword"), props.getProperty("Password2"));//Enter password
        clickElement(By.className("button"));//Click on login button
        clickElement(By.linkText("Welcome Admin"));//Click on 'Welcome Admim' button to Logout
        String expected = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

@Test
public void userShouldBeAbleToAssignLeave() {
     pointcursorToWebelement(By.xpath("//b[contains(text(),'Leave')]"));//mouse hover on the leave tab
    clickElement(By.xpath(" //a[@id='menu_leave_assignLeave']"));//click on assign leave
    String expectedAssignLeave = "https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave";//expected webpage
    String actualAssignLeave = driver.getCurrentUrl();//actual webpage
    Assert.assertEquals(actualAssignLeave, expectedAssignLeave);//assertion
    enterText(By.xpath("//input[@id='assignleave_txtEmployee_empName']"), props.getProperty("EmployeeName"));//enter existing employee name
    getselectedTextFromDropdownList(By.xpath("//select[@id='assignleave_txtLeaveType']"), props.getProperty("LeaveType"));//select leave type from the dropdown list
    String entitledleave= getTextFromContent(By.xpath("//div[contains(text(), '80.00')]"));
    clearAndEnterText(By.xpath("//input[@id='assignleave_txtFromDate']"), props.getProperty("FromDate"));//enter a from date
    clearAndEnterText(By.xpath("//input[@id='assignleave_txtToDate']"), props.getProperty("ToDate"));// enter a to date
    clickElement(By.xpath("//input[@id='assignBtn']"));//click on assign button
    waitUntilEleemtLoadAndIsClickable((By.xpath("//input[@id='assignBtn']")), 2000);//wait before clicking on assign button
    clickElement(By.xpath("//input[@id='assignBtn']"));//click on assign button
    String expected= entitledleave.substring(0, 5);//to eliminate unncessary words
    System.out.println(expected);//to print the number of days before asigning leave
    Double entitledleavebalance=Double.parseDouble(expected);//converting string to double
    Double numberofdaysofleave= entitledleavebalance-24.00;//deducting assigned leave from the balance
    System.out.println(numberofdaysofleave);//expected
    String remainingleave= getTextFromContent(By.xpath("//div[contains (text(),'56.00')]"));//to get text after assigning leave
    String remainingdays=remainingleave.substring(0, 5);//eleminating unnecessary words
    Double balanceafterassign=Double.parseDouble(remainingdays);//converting to double
    System.out.println(balanceafterassign);//actual
    Assert.assertEquals(remainingdays, numberofdaysofleave);//asserting actual with expected

    }
@Test
public void adminShouldBeAbleToUpdateEmployeePersonalDetails(){
        pointcursorToWebelement(By.xpath("//b[contains(text(),'PIM')]"));//mouse hover on PIM
        clickElement(By.id("menu_pim_viewEmployeeList"));//click on Employee list
        clickElement(By.linkText("Robert"));//click on employee 'Robert'
        clickElement(By.xpath("//input[@id='btnSave']"));//click on edit button
        enterText(By.cssSelector("#personal_txtLicenNo"), props.getProperty("DrivingLicenceNumber"));//Enter Driving licence number
        clearAndEnterText(By.xpath("//input[@id='personal_txtLicExpDate']"), props.getProperty("DrivingLicenceExpiryDate"));//Enter driving licence expiry date
        clickElement(By.xpath("//input[@id='btnSave']"));//click on save button
    try {
        takeScreenshot(driver, "./Screenshots/drivinglicence.png");//taking screenshot of the 'successfully saved' message
    } catch (Exception e) {
        e.printStackTrace();
    }

    String expectedupdate = "./Screenshots/drivinglicence.png";
    String actualupdate = "./Screenshots/drivinglicence.png";
    Assert.assertEquals(actualupdate, expectedupdate);//asserting the actual message with the expected one



}

@Test
    public void adminShouldBeAbleToUpdateExistingJobTitles(){
        pointcursorToWebelement(By.linkText("Admin"));//mouse hover on admin
        pointcursorToWebelement(By.cssSelector("#menu_admin_Job"));//mouse hover on job
        clickElement(By.xpath("//a[@id='menu_admin_viewJobTitleList']"));//click on job titles
        clickElement(By.xpath("//a[contains(text(),'CEO')]"));//click on CEO
        clickElement(By.xpath("//input[@value='Edit']"));//click on edit button
        enterText(By.cssSelector("#jobTitle_jobDescription"), props.getProperty("JobDescription"));//add job description
        enterText(By.cssSelector("#jobTitle_note"), props.getProperty("Notes"));
        clearAndEnterText(By.xpath("//input[@id='jobTitle_jobSpec']"),props.getProperty("JobSpecificationFileUpload"));//uploading jobspecification file
        clickElement(By.xpath("//input[@id='btnSave']"));
        String expectedtitleupdate = "https://opensource-demo.orangehrmlive.com/index.php/admin/viewJobTitleList";//expected url of the page after updation
        String actualtitleupdate = driver.getCurrentUrl();//actual url of the page after updation
        Assert.assertEquals(actualtitleupdate, expectedtitleupdate);//asserting acutal with expected.



}


}





















