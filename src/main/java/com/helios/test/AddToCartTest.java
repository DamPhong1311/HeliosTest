import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AddToCartTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:\\Test\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String[] productLinks = {
                "https://helios.vn/collections/nhan-bac-nam/products/hawaii-helios-silver",
                "https://helios.vn/collections/nhan-bac-nam/products/nhan-bac-nam-lotusgot-helios-silver",
                "https://helios.vn/collections/nhan-bac-nam/products/enso-lotus-helios-silver"
        };

        try {
            // THEM 3 SAN PHAM KHAC NHAU VAO GIO
            for (String link : productLinks) {
                driver.get(link);
                WebElement addBtn = wait
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
                System.out.println("Da them san pham: " + link);
                Thread.sleep(2000); // doi cap nhat
            }

            //  XOA TOAN BO SAN PHAM KHOI GIO

            while (true) {
                try {
                    // Tim lai nut xoa moi nhat
                    List<WebElement> deleteBtns = driver
                            .findElements(By.cssSelector(".cart-drawer-remove-btn .delete"));
                    if (deleteBtns.isEmpty())
                        break;

                    // Click nut xoa dau tien
                    deleteBtns.get(0).click();
                    System.out.println("Da xoa mot san pham khoi gio");

                    // Doi DOM cap nhat
                    Thread.sleep(1500);
                } catch (StaleElementReferenceException e) {
                    System.out.println(" Canh bao: DOM bi thay doi, se thu lai");
                }
            }

            //  CHO TOI KHI GIO HANG TRONG
            Thread.sleep(2000);
            if (driver.getPageSource().toLowerCase().contains("gio hang cua ban dang trong")) {
                System.out.println(" Gio hang da duoc xoa het");
            } else {
                System.out.println(" Loi: Gio hang chua duoc xoa het");
            }

            // ➕ THEM MOT SAN PHAM NHIEU LAN
            driver.get("https://helios.vn/collections/nhan-bac-nam/products/hawaii-helios-silver");
            for (int i = 0; i < 3; i++) {
                WebElement addBtn = wait
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
                System.out.println("Them lan thu " + (i + 1));
                Thread.sleep(2000);
            }

            //  KIEM TRA SL SAN PHAM
            WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".cart-drawer-quantity-selector input")));
            String value = quantity.getAttribute("value");

            if (value.equals("3")) {
                System.out.println(" Da them san pham dung so luong (3)");
            } else {
                System.out.println(" Loi: So luong khong dung, thay: " + value);
            }
            // 🧮 KIEM TRA TONG GIA
            try {
                WebElement totalPriceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".cart-drawer-footer-row span")));

                String rawText = totalPriceEl.getText(); // Vi du: "2.835.000 VND"
                String numberOnly = rawText.replaceAll("[^\\d]", ""); // Bỏ ký tự không phải số -> "2835000"

                int expected = 945000 * 3;
                int actual = Integer.parseInt(numberOnly);

                if (actual == expected) {
                    System.out.println(" Tong gia dung: " + actual);
                } else {
                    System.out.println(" Loi: Tong gia sai. Thay: " + actual + " - Ky vong: " + expected);
                }

            } catch (Exception e) {
                System.out.println(" Loi khi kiem tra tong gia");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
