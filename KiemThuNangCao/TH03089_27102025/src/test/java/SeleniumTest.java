import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void testTimKiem() throws InterruptedException {
        driver.get("https://vitimex.com.vn/");
        WebElement logoSearch = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/header/div/div/div/div[3]/div/div[1]/a"))
        );
        logoSearch.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"inputSearchAuto\"]")).sendKeys("Polo");
        Thread.sleep(2000);
        WebElement clickSearch = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-header-btn\"]"))
        );
        clickSearch.click();
        System.out.println("Đã tìm kiếm thành công");
        assertEquals("https://vitimex.com.vn/search?q=Polo", driver.getCurrentUrl());
    }


    @Test(priority = 2)
    public void testThemVaoGioHang() throws InterruptedException {
        driver.get("https://vitimex.com.vn/search?q=Polo");
        WebElement aoClick = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-page\"]/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/h3/a"))
        );
        aoClick.click();
        Thread.sleep(2000);
        WebElement size = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"variant-swatch-2\"]/div[2]/div[5]/label"))
        );
        size.click();
        Thread.sleep(2000);
        WebElement addToCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btn-addtocart\"]"))
        );
        addToCart.click();
        Thread.sleep(2000);
        System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
