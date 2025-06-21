package com.helios.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;

public class FilterByCategoryTest {
    public void run() {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            driver.get("https://helios.vn");
            new Thread(() -> {
                try {
                    Thread.sleep(5000); // Cho popup có thời gian xuất hiện
                    WebElement popupClose = driver.findElement(By.cssSelector("svg[role='img'][title='Close dialog']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popupClose);
                    System.out.println("Thread: Da dong popup");
                } catch (Exception e) {
                    System.out.println("Thread: Popup khong ton tai");
                }
            }).start();

            try {
                WebElement iconMenu = wait
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.icon-menu")));
                iconMenu.click();
                System.out.println("Da mo menu bang icon-menu (man hinh nho)");
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Khong can mo icon-menu (man hinh du lon)");
            }

            WebElement menuBtn = wait.until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector("li.has-children > a[aria-haspopup='true']")));
            menuBtn.click();

            WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("NHẪN BẠC NAM")));
            categoryLink.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-list")));
            System.out.println("Loc theo danh muc san pham thanh cong");

            WebElement giaFilter = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//summary[contains(text(), 'Giá')]")));
            giaFilter.click();
            WebElement giaCheckbox = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='500.000 - 999.000 VND']")));
            giaCheckbox.click();
            Thread.sleep(3000);

            WebElement sizeFilter = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//summary[contains(text(), 'Size')]")));
            sizeFilter.click();
            WebElement sizeCheckbox = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Size 6']")));
            sizeCheckbox.click();
            Thread.sleep(3000);

            int count = driver.findElements(By.cssSelector("div.product-block")).size();
            System.out.println(count > 0 ? "Loc thanh cong - Co " + count + " san pham" : "Loc that bai");

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
