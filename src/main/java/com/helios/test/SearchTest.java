package com.helios.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SearchTest {
    public void run() {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            performSearch(driver, wait, "nhan");
            performSearch(driver, wait, "abcxyz123");
            performSearch(driver, wait, "");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private void performSearch(WebDriver driver, WebDriverWait wait, String keyword) throws InterruptedException {
        driver.get("https://helios.vn");
        WebElement searchIcon = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav-search[aria-label='Search']")));
        searchIcon.click();
        WebElement searchInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='q']")));
        searchInput.clear();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);

        Thread.sleep(3000);
        // boolean noResult = driver.findElements(By.cssSelector("h3.align-centre.no-results")).size() > 0;
        // System.out.println(noResult ? "Khong tim thay san pham cho: " + keyword : "Tim thay ket qua cho: " + keyword);
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("search")) { // Kiem tra co the tim thay ket qua hay khong
            boolean isNoResult = driver.findElements(By.cssSelector("h3.align-centre.no-results")).size() > 0;
            if (isNoResult) {
                System.out.println("Khong tim thay san pham phu hop cho tu khoa: " + keyword);
            } else {
                System.out.println("Tim thay ket qua cho tu khoa: " + keyword);
            }
        } else {
            System.out.println("Khong chuyen trang khi tim kiem tu khoa: " + keyword);
        }
    }
}