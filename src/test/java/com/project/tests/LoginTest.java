package com.project.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    @Test
    public void loginWithValidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.helpthemread.com/login");

        // Maximise window
        driver.manage().window().maximize();

        // Locate and fill Email
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("oyebanjiyusuf3@gmail.com");

        // Locate and fill Password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("12345678");

        // Click Log In
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // ✅ Wait and handle success alert if it appears
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText());
            alert.accept(); // Accept the success popup
        } catch (Exception e) {
            System.out.println("No alert appeared after login.");
        }

        // Wait for redirect
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // ✅ Assert: User should land on parent dashboard
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("parent-dashboard"), "Login failed or did not redirect to parent dashboard.");

        driver.quit();
    }
}
