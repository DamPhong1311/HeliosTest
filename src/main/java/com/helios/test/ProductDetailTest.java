import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductDetailTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            System.out.println("🔎 FUNC009 - Xem chi tiet san pham");

            driver.get("https://helios.vn/collections/nhan-bac-nam");

        
            // Click vao san pham dau tien
            WebElement imageLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.image-inner")));
            String expectedHref = imageLink.getAttribute("href");
            imageLink.click();

            wait.until(ExpectedConditions.urlContains("/products/"));

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains(expectedHref)) {
                System.out.println("✅ Da chuyen den trang chi tiet: " + currentUrl);
            } else {
                System.out.println("⚠️ Trang chi tiet khong khop voi link da click");
            }

            // ✅ Kiểm tra tiêu đề sản phẩm
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1.product-area__details__title")));
            if (title.isDisplayed()) {
                System.out.println("✅ Tieu de san pham: " + title.getText());
            } else {
                System.out.println("❌ Khong thay tieu de san pham");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
