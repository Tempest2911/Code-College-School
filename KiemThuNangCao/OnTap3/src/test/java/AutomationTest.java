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

public class AutomationTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void testClickAoNamMenu() {
        driver.get("https://vitimex.com.vn/");
        WebElement aoNamMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-menu\"]/ul/li[2]/a"))
        );
        aoNamMenu.click();
        System.out.println("Đã truy cập");
        assertEquals("https://vitimex.com.vn/collections/ao-nam", driver.getCurrentUrl());
    }

    @Test(priority = 2)
    public void testTruyCapVao1Ao() {
        driver.get("https://vitimex.com.vn/collections/ao-nam");
        WebElement productLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"collection-page\"]/div[1]/div[6]/div/div/div/div[1]/div/div[1]/div/div[2]/h3/a"))
        );
        productLink.click();
        assertEquals("https://vitimex.com.vn/products/atr5033", driver.getCurrentUrl());
    }

    @Test(priority = 3)
    public void testThemVaoGioHang() {
        driver.get("https://vitimex.com.vn/products/atr5033");
        WebElement aoNamMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"variant-swatch-2\"]/div[2]/div[5]/label"))
        );
        aoNamMenu.click();
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btn-addtocart\"]"))
        );
        addToCartBtn.click();
        System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
