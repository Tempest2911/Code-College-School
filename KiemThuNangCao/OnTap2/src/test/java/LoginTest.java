import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get(" https://practicetestautomation.com/practice-test-login");
        Thread.sleep(2000); // Just for demonstration purposes, use explicit waits in real tests
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        Assert.assertTrue(driver.getPageSource().contains("Logged In Successfully"));
        Assert.assertTrue(driver.getPageSource().contains("Congratulations student. You successfully logged in!"));
        System.out.println("Login test passed.");
        throw new NullPointerException();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
