import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SearchTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://helios.vn");

            // FUNC004 - Tim san pham hop le
            System.out.println("FUNC004 - Tim san pham hop le");
            performSearch(driver, wait, "nhan");

            // FUNC005 - Tim san pham khong ton tai
            System.out.println("FUNC005 - Tim san pham khong ton tai");
            performSearch(driver, wait, "abcxyz123");

            // FUNC006 - Tim kiem voi chuoi rong
            System.out.println("FUNC006 - Tim kiem rong");
            performSearch(driver, wait, "");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

  private static void performSearch(WebDriver driver, WebDriverWait wait, String keyword) throws InterruptedException {
    // Mo lai trang chu neu can
    driver.get("https://helios.vn");

    // Buoc 1: Click vao icon tim kiem
    WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("a.nav-search[aria-label='Search']")));
    searchIcon.click();

    // Buoc 2: Nhap tu khoa vao o input
    WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("input[name='q']")));
    searchInput.clear();
    searchInput.sendKeys(keyword);
    searchInput.sendKeys(Keys.ENTER);

    // Buoc 3: Cho ket qua va danh gia
    Thread.sleep(3000); // doi ket qua

    String currentUrl = driver.getCurrentUrl();
    if (currentUrl.contains("search")) {
        // Kiem tra co the tim thay ket qua hay khong
        boolean isNoResult = driver.findElements(By.cssSelector("h3.align-centre.no-results")).size() > 0;
        if (isNoResult) {
            System.out.println("Khong tim thay san pham phu hop cho tu khoa: " + keyword);
        } else {
            System.out.println("Tim thay ket qua cho tu khoa: " + keyword);
        }
    } else {
        System.out.println("Khong chuyen trang khi tim kiem tu khoa: " + keyword);
    }

    System.out.println("-----------------------------");
}

}
