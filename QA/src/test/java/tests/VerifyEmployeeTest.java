package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import pages.EmployeeAdd;
import pages.VerifyEmployee;
import utils.ConfigReader;
import utils.copyReportToDownloads;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class VerifyEmployeeTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private EmployeeAdd employeeAdd;
    private VerifyEmployee verifyEmployee;

    private List<String> employeeIdList = new ArrayList<>();
    private List<String[]> employeeNameList = new ArrayList<>();

    @BeforeClass
    public void setUpTest() {
        setup();  // Launch browser and load URL

        loginPage = new LoginPage(driver);
        loginPage.loginAs(ConfigReader.get("username"), ConfigReader.get("password"));

        dashboardPage = new DashboardPage(driver);
        dashboardPage.hoverAndClickPIM();

        employeeAdd = new EmployeeAdd(driver);
        verifyEmployee = new VerifyEmployee(driver);
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

    @Test(priority = 1, dataProvider = "employeeData")
    public void addEmployeesAndCaptureIds(String firstName, String middleName, String lastName) {
        employeeAdd.clickAddButton();
        employeeAdd.enterFirstName(firstName);
        employeeAdd.enterMiddleName(middleName);
        employeeAdd.enterLastName(lastName);
        employeeAdd.clickSave();

        String empId = employeeAdd.getEmployeeId();
        System.out.println("Captured Employee ID: " + empId);
        employeeIdList.add(empId);
        employeeNameList.add(new String[]{firstName, lastName});

        employeeAdd.waitForPersonalDetailsTab();
        dashboardPage.hoverAndClickPIM(); // Return to PIM for next add
    }

    @Test(priority = 2, dependsOnMethods = "addEmployeesAndCaptureIds")
    public void verifyAddedEmployees() throws TimeoutException {
        for (int i = 0; i < employeeIdList.size(); i++) {
            String empId = employeeIdList.get(i);
            String firstName = employeeNameList.get(i)[0];
            String lastName = employeeNameList.get(i)[1];

            dashboardPage.hoverAndClickPIM();  // Ensure on search page
            boolean isVerified = verifyEmployee.verifyEmployeeByIdAndName(empId, firstName, lastName);

            Assert.assertTrue(isVerified, "Employee ID or Name not verified: " + empId);
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
