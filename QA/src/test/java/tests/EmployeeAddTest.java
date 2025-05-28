package tests;

import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import pages.EmployeeAdd;
import utils.ConfigReader;
import utils.copyReportToDownloads;

public class EmployeeAddTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private EmployeeAdd employeeAdd;
 // In EmployeeAddTest.java
    private static List<String> employeeIdList = new ArrayList<>();


    @BeforeClass
    public void setUpTest() {
        setup(); // Launch browser and load URL

        // Step 1: Login
        loginPage = new LoginPage(driver);
        loginPage.loginAs(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to Dashboard > PIM
        dashboardPage = new DashboardPage(driver);
        dashboardPage.hoverAndClickPIM();

        // Step 3: Initialize EmployeeAdd page object
        employeeAdd = new EmployeeAdd(driver);
    }


    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() {
        String[] employees = {
            ConfigReader.get("employee1"),
            ConfigReader.get("employee2"),
            ConfigReader.get("employee3"),
            ConfigReader.get("employee4")
        };

        Object[][] data = new Object[employees.length][3];
        for (int i = 0; i < employees.length; i++) {
            data[i] = employees[i].split(",");
        }
        return data;
    }

    @Test(dataProvider = "employeeData")
    public void testAddNewEmployee(String firstName, String middleName, String lastName) {
        employeeAdd.clickAddButton();
        employeeAdd.enterFirstName(firstName);
        employeeAdd.enterMiddleName(middleName);
        employeeAdd.enterLastName(lastName);
        employeeAdd.clickSave();

        String empId = employeeAdd.getEmployeeId(); // this calls the method in EmployeeAdd.java
        System.out.println("Captured Employee ID: " + empId);
        employeeIdList.add(empId);
       
        
        // Wait for Personal Details tab to confirm employee added and page loaded
        employeeAdd.waitForPersonalDetailsTab();
        employeeAdd.getEmployeeId();

        // Navigate back to PIM page for next employee addition
        dashboardPage.hoverAndClickPIM();

        // Add assertion if needed, e.g. verify success message
    }

    @AfterClass
    public void tearDownTest() {
        if(driver != null) {
            driver.quit();   // closes browser
        }
    	copyReportToDownloads.copyReportToDownloads1();
    }
}
