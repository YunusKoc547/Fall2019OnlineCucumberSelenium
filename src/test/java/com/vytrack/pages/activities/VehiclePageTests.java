package com.vytrack.pages.activities;

import com.vytrack.utilities.BrowserUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class VehiclePageTests {
    private WebDriver driver;

    private String username = "storemanager85";
    private String password = "UserUser123";

    //https is a secured version of http protocol
    //http is a hypertext transfer protocol that every single website is using nowadays
    //https - data encrypt
    private String URL = "https://qa2.vytrack.com/user/login";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");

    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet)]");
    private By subtitleBy = By.className("oro-subtitle");
    private By pageNumberBy = By.cssSelector("input[type='number']");

    @Test
    public void verifyPageSubTitle()throws Exception{
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtilities.wait(5);

        Actions actions = new Actions(driver);
        // move to element instead of click
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        // perform - to execute command
        // every action should end with perform()
        BrowserUtilities.wait(5);

        driver.findElement(fleetBy).click();
        BrowserUtilities.wait(2);

        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtilities.wait(5);

        // find subtitle element
        WebElement subTitleElement = driver.findElement(subtitleBy);
        System.out.println(subTitleElement.getText());

        String expected = "All Cars";
        String actual = subTitleElement.getText();

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void verifyPageNumber() {
        String expected = "1";
        String actual = driver.findElement(pageNumberBy).getAttribute("value");

        Assert.assertEquals(actual, expected);
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
