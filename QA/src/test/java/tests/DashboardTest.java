package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import utils.ConfigReader;
import utils.copyReportToDownloads;

public class DashboardTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void setupTest() {
        setup();
        ConfigReader.loadConfig();

        // Login before navigating to Dashboard
        loginPage = new LoginPage(driver);
        loginPage.loginAs(ConfigReader.get("username"), ConfigReader.get("password"));

        dashboardPage = new DashboardPage(driver);
    }


    @Test
    public void testHoverAndClickPIM() {
        dashboardPage.hoverAndClickPIM();
        System.out.println("Hovered and clicked on PIM module.");
    }

    @AfterClass
    public void tearDownTest() {
        if(driver != null) {
            driver.quit();   // closes browser
        }
    	copyReportToDownloads.copyReportToDownloads1();
    }
}
