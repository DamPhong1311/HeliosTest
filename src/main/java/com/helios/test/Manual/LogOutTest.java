package com.helios.test.Manual;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LogOutTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Mở trang chính hoặc trang tài khoản
            driver.get("https://helios.vn/account");

            // Chờ và click nút "Đăng xuất"
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.logout-button")));
            logoutBtn.click();
            System.out.println("🚪 Da click Dang xuat");

            // Kiểm tra đã đăng xuất thành công chưa
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/account/login"),
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Đăng nhập")
            ));
            System.out.println("✅ Da dang xuat thanh cong");

        } catch (Exception e) {
            System.out.println("❌ Loi khi dang xuat");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
