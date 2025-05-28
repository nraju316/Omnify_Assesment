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
        // Use ConfigReader to get credentials from config.properties
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        loginPage.loginAs(username, password);
        System.out.println("Login attempted with valid credentials: " + username);
    }

    @AfterClass
    public void tearDownTest() {
        if(driver != null) {
            driver.quit();   // closes browser
        }
    	copyReportToDownloads.copyReportToDownloads1();
    }
}

