package MavenHomeWork2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AutosuggestiveboxGoogle extends Utils {
    public static void main(String[] args) {
        String search=null;
        launchingChromeDriver();

        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("hello");
        List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']/li/descendant::div[@class='sbl1']"));
        System.out.println(list.size());

        for (int i = 0; i <list.size() ; i++) {
            search=list.get(i).getText();
            if(search.contains("hindi")){
                list.get(i).click();
                break;
            }
            else if (!search.contains("hindi"));
            System.out.println("This item is not present in the list");
            break;

            }

        }


    }





