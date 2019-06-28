import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LoginPortal {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
        // Instance of the browser
        WebDriver driver = new ChromeDriver();

        // Open the portal
        String appUrl = "https://portal.aait.edu.et";
        driver.get(appUrl);

        // Login to portal
        driver.findElement(By.xpath("//*[@id=\"UserName\"]")).sendKeys("ATR/6676/09");
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("0180");
        driver.findElement(By.xpath("//*[@id=\"home\"]/div[2]/div[2]/form/div[4]/div/button")).click();

        String reportLink = driver.findElement(By.id("m2")).findElement(By.className("dropdown-menu")).findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).get(0).getAttribute("href");
        driver.navigate().to(reportLink);

        // Get the table that contains grades
        String table = driver.findElement(By.className("table")).getText();

        // Write it to a file
        PrintWriter writer = new PrintWriter("grades", "UTF-8");
        writer.print(table);
        writer.close();

        Thread.sleep(1000);

        //Close browser
        driver.quit();


    }


}
