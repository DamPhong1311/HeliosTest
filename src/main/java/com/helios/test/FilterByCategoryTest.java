import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterByCategoryTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            driver.get("https://helios.vn");

            try {
                WebElement iconMenu = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("span.icon-menu")));
                iconMenu.click();
                System.out.println("Đã mở menu bằng icon-menu (màn hình nhỏ)");
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Không cần mở icon-menu (màn hình đủ lớn)");
            }

            // B1: Vào danh mục "NHẪN BẠC NAM"
            WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("li.has-children > a[aria-haspopup='true']")));
            menuBtn.click();

            WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("NHẪN BẠC NAM")));
            categoryLink.click();

            // B2: Chờ danh mục load
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.product-list")));
            System.out.println("Loc theo danh muc san pham thanh cong ");
            // B3: Lọc theo Giá
            WebElement giaFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//summary[contains(text(), 'Giá')]")));
            giaFilter.click();
            System.out.println("Đã mở bộ lọc Giá");

            // Chọn giá < 1.000.000 VND (thường là radio/checkbox đầu tiên)
            WebElement giaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='500.000 - 999.000 VND']")));
            giaCheckbox.click();
            System.out.println("Đã loc theo gia thanh cong");

            Thread.sleep(3000); // đợi trang reload

            // B4: Lọc theo Size
            WebElement sizeFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//summary[contains(text(), 'Size')]")));
            sizeFilter.click();
            System.out.println("Đã mở bộ lọc Size");

            WebElement sizeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Size 6']")));
            sizeCheckbox.click();
            System.out.println("Đã loc theo size thanh cong");

            Thread.sleep(3000); // đợi lọc xong

            // B5: Kiểm tra kết quả
            WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.product-list")));
            int count = driver.findElements(By.cssSelector("div.product-block")).size();

            if (count > 0) {
                System.out.println(" Lọc theo Giá và Size thành công - Có " + count + " sản phẩm");
            } else {
                System.out.println(" Lọc thất bại - Không có sản phẩm");
            }

        } catch (Exception e) {
            System.out.println(" Xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
