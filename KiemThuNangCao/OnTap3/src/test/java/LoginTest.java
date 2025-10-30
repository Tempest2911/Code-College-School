import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testRegister() throws InterruptedException {
        driver.get("https://vitimex.com.vn/account/register");
        driver.findElement(By.id("last_name")).sendKeys("Nguyễn");
        driver.findElement(By.id("first_name")).sendKeys("Phong");
        driver.findElement(By.id("radio2")).click();
        driver.findElement(By.id("birthday")).sendKeys("11/29/2007");
        driver.findElement(By.id("email")).sendKeys("dragonroblox2k7@gmail.com");
        driver.findElement(By.id("password")).sendKeys("ndp291107");
        driver.findElement(By.className("btn-primary")).click();
        System.out.println("Register test passed.");
        Thread.sleep(3000);
        assertEquals("https://vitimex.com.vn/account", driver.getCurrentUrl());
    }

    @Test(priority = 2)
    public void testLogin() throws InterruptedException {
        driver.get("https://vitimex.com.vn/account/login");
        Thread.sleep(2000);
        driver.findElement(By.id("customer_email")).sendKeys("dragonroblox2k7@gmail.com");
        driver.findElement(By.id("customer_password")).sendKeys("ndp291107");
        driver.findElement(By.className("btn-signin")).click();
        System.out.println("Login test passed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
