package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Trial extends Utils {

    public static void main(String[] args) {
        launchingChromeDriver();//method called to launch the browser
        driver.get("https://www.spicejet.com/");//web page url given
        clickElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']"));//click on round trip
        if (isWebElementSelected(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']"))) {
            System.out.println("Round trip selected");
        } else {
            System.out.println("Round trip is not selected");
        }
        if (isWebElementEnabled(By.xpath("//input[@id='ctl00_mainContent_view_date2']"))) {
            System.out.println("Return date field is enabled");
        } else {
            System.out.println("Rretrun date field is not enabled");


        }
        clickElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));//click on departure city
        List<WebElement> dep_city = driver.findElements(By.cssSelector("td[class='mapbg'] ul  a"));
        for (WebElement city : dep_city) {
            if (city.getText().contains("Srinagar (SXR)")) {
                city.click();
                break;
            }

        }
        List<WebElement> cityArr = driver.findElements(By.xpath("(//td[@class='mapbg'])[2]//ul//a"));
        for (WebElement cityArrival : cityArr) {
            if (cityArrival.getText().contains("Delhi (DEL)")) {
                cityArrival.click();
                break;
            }
        }
        while(!driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']//div[@class='ui-datepicker-title']")).getText().contains("December"));
        {
            clickElement(By.xpath("a[data-event='click']"));
        }
        //clickElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']"));
        //WebElement table =driver.findElement(By.cssSelector(".ui-datepicker-calendar"));//selecting the whole table of date
        //int rowcount = table.findElements(By.cssSelector(".ui-state-default")).size();
        //System.out.println(rowcount);


    }
}



