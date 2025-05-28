package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeAdd {
    private WebDriver driver;
    private WebDriverWait wait;

    // Updated Locators (no h6 involved)
    private By addButton = By.xpath("//button[@type='button' and contains(., 'Add')]");

    private By firstNameInput = By.cssSelector("input.orangehrm-firstname");
    private By middleNameInput = By.cssSelector("input.orangehrm-middlename");
    private By lastNameInput = By.cssSelector("input.orangehrm-lastname");
    private By employeeIdInput = By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private By personalDetailsTab = By.xpath("//a[contains(@href, 'viewPersonalDetails') and text()='Personal Details']");




    public EmployeeAdd(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // No need to wait for the title here since it doesn't exist
    }
    
    public void waitForPersonalDetailsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsTab));
    }

    public void waitForAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)); // Ensure form is visible
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        WebElement middleNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(middleNameInput));
        middleNameElem.clear();
        middleNameElem.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    public String getEmployeeId() {
        try {
            WebElement empIdElem = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdInput));
            wait.until(driver -> !empIdElem.getAttribute("value").trim().isEmpty());
            String value = empIdElem.getAttribute("value").trim();
            return value;
        } catch (Exception e) {
            System.out.println("Error retrieving Employee ID: " + e.getMessage());
            return "";
        }
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
    

}
