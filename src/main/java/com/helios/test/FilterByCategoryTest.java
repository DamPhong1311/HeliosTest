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
                Thread.sleep(1000); // chờ menu hiện
            } catch (Exception e) {
                System.out.println("Không cần mở icon-menu (màn hình đủ lớn)");
            }

            // B1: Click nut MENU
            WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("li.has-children > a[aria-haspopup='true']")));
            menuBtn.click();

            // B2: Cho danh sach hien ra va click vao "Nhan bac nam"
            WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("NHẪN BẠC NAM")));
            categoryLink.click();

            // B3: Cho trang danh muc load va kiem tra ket qua
            WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.product-list")));

            String countText = productList.getAttribute("data-result-count");
            int count = Integer.parseInt(countText.trim());

            if (count > 0) {
                System.out.println("Loc thanh cong - Co " + count + " san pham hien thi");
            } else {
                System.out.println(" Loc that bai - Khong co san pham");
            }

        } catch (Exception e) {
            System.out.println("Xay ra loi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
