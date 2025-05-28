package tests;

import base.BaseTest;
import utils.copyReportToDownloads;

import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void launchBrowser() {
        setup();
        loginPage = new LoginPage(driver); // initialize page object
    }

    @Test
    public void testValidLogin() {
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        try {
            loginPage.loginAs(username, password);
            System.out.println("✅ Login attempted with valid credentials: " + username);
            
            // Optional: Add assertion or post-login check
            // e.g., check if dashboard is visible or URL contains "/dashboard"
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("dashboard")) {
                System.out.println("✅ Login successful: redirected to dashboard.");
            } else {
                System.out.println("❌ Login failed: dashboard not reached.");
            }

        } catch (Exception e) {
            System.out.println("❌ Login failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDownTest() {
        if(driver != null) {
            driver.quit();   // closes browser
        }
    	copyReportToDownloads.copyReportToDownloads1();
    }
}

