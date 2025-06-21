package com.helios.test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductDetailTest {
    public void run() {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://helios.vn/collections/nhan-bac-nam");
            WebElement imageLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.image-inner")));
            String expectedHref = imageLink.getAttribute("href");
            imageLink.click();

            wait.until(ExpectedConditions.urlContains("/products/"));
            String currentUrl = driver.getCurrentUrl();
            System.out.println(currentUrl.contains(expectedHref) ? "Da chuyen den chi tiet: " + currentUrl : "Trang chi tiet khong khop");

            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.product-area__details__title")));
            System.out.println("Xem thanh cong.Tieu de: " + title.getText());

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
