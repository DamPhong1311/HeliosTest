package com.helios.test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App 
{
    public static void main(String[] args) {
        System.out.println("=== BAT DAU TEST ===\n");
        
        System.out.println("Test , size\n");
        new FilterByCategoryTest().run();
        System.out.println("\n-----------------------------\n");
        
        System.out.println("Test xem chi tiết san pham\n");
        new ProductDetailTest().run();
        System.out.println("\n-----------------------------\n");

         System.out.println("Test tim kiem\n");
        new SearchTest().run();
        System.out.println("\n-----------------------------\n");

        System.out.println("Test gio hang\n");
        new AddToCartTest().run();

        System.out.println("\n=== KET THUC TEST ===");
    }
}
