package com.helios.test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AddToCartTest {
    public void run() {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String[] productLinks = {
            "https://helios.vn/collections/nhan-bac-nam/products/hawaii-helios-silver",
            "https://helios.vn/collections/nhan-bac-nam/products/nhan-bac-nam-lotusgot-helios-silver",
            "https://helios.vn/collections/nhan-bac-nam/products/enso-lotus-helios-silver"
        };

        try {
            for (String link : productLinks) {
                driver.get(link);
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
                Thread.sleep(2000);
            }

            while (true) {
                List<WebElement> deleteBtns = driver.findElements(By.cssSelector(".cart-drawer-remove-btn .delete"));
                if (deleteBtns.isEmpty()) break;
                deleteBtns.get(0).click();
                Thread.sleep(1500);
            }

            Thread.sleep(2000);
            if (driver.getPageSource().toLowerCase().contains("gio hang cua ban dang trong")) {
                System.out.println("Gio hang da duoc xoa het");
            }

            driver.get("https://helios.vn/collections/nhan-bac-nam/products/hawaii-helios-silver");
            for (int i = 0; i < 3; i++) {
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
                Thread.sleep(2000);
            }

            WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-drawer-quantity-selector input")));
            String value = quantity.getAttribute("value");
            System.out.println(value.equals("3") ? "Da them dung 3 san pham" : "So luong sai: " + value);

            WebElement totalPriceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-drawer-footer-row span")));
            String rawText = totalPriceEl.getText();
            String numberOnly = rawText.replaceAll("[^\\d]", "");
            int expected = 945000 * 3;
            int actual = Integer.parseInt(numberOnly);
            System.out.println(actual == expected ? "Tong gia dung: " + actual : "Tong gia sai: " + actual);

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
