package com.helios.test.Manual;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpTest {
    public static void main(String[] args) {
        // Chỉ định đường dẫn chromedriver (sửa đúng file bạn đã tải)
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");

        // Khởi tạo trình duyệt
        WebDriver driver = new ChromeDriver();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            // Mở trang đăng ký
            driver.get("https://helios.vn/account/register");

            // Nhập thông tin đăng ký
            WebElement firstName = driver.findElement(By.id("first_name"));
            WebElement lastName = driver.findElement(By.id("last_name"));
            WebElement email = driver.findElement(By.id("email"));
            WebElement sdt = driver.findElement(By.id("email"));
            WebElement pass = driver.findElement(By.id("password"));
            WebElement btn = driver.findElement(By.cssSelector("input[type='submit'][value='Tạo tài khoản']"));

            firstName.sendKeys("Nguyen");
            lastName.sendKeys("Test");
            email.sendKeys("test" + System.currentTimeMillis() + "@example.com"); 
            sdt.sendKeys("0358262226");
            pass.sendKeys("123456");
            btn.click();

            // Đợi 3 giây (tùy máy)
            Thread.sleep(3000);

            // Kiểm tra kết quả
            String url = driver.getCurrentUrl();
            if (url.contains("/account")) {
                System.out.println("Đăng ký thành công!");
            } else {
                System.out.println("Đăng ký thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
