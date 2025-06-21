package com.helios.test.Manual;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
          
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // FUNC003 - Sai email
        runLoginTest("FUNC003 - Sai email",
                "saiemail@gmail.com", "Nguoitake9173123@");
        // FUNC002 - Sai mật khẩu
        runLoginTest("FUNC002 - Sai mật khẩu",
                "phong9173123@gmail.com", "saimatkhau");
        // FUNC001 - Đăng nhập thành công
        runLoginTest("FUNC001 - Đăng nhập thành công",
                "phong9173123@gmail.com", "Nguoitake9173123@");

                //test logout
            // driver.get("https://helios.vn/account");

            // // Chờ và click nút "Đăng xuất"
            // WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
            //         By.cssSelector("a.logout-button")));
            // logoutBtn.click();
            // System.out.println("🚪 Da click Dang xuat");

            // // Kiểm tra đã đăng xuất thành công chưa
            // wait.until(ExpectedConditions.or(
            //         ExpectedConditions.urlContains("/account/login"),
            //         ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Đăng nhập")));
            // System.out.println("✅ Da dang xuat thanh cong");

    }

    private static void runLoginTest(String testName, String emailInput, String passwordInput) {
        System.out.println("🔎 Bắt đầu test: " + testName);
           WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("https://helios.vn/account/login");

            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_email")));
            WebElement password = driver.findElement(By.id("customer_password"));
            WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Đăng nhập']"));

            email.clear();
            password.clear();
            email.sendKeys(emailInput);
            password.sendKeys(passwordInput);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            Thread.sleep(5000); // đợi phản hồi

            boolean hasError = driver.findElements(By.className("errors")).size() > 0;
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("/account") && !currentUrl.contains("login")) {
                System.out.println(" Đăng nhập thành công");
            } else if (hasError) {
                System.out.println("Đăng nhập thất bại ");
            } else {
                System.out.println(" lỗi ");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
