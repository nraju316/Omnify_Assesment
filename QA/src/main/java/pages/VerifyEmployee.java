package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyEmployee {
    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeIdInput = By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private By searchButton = By.xpath("//button[@type='submit' and contains(., 'Search')]");
    private By hrLine = By.xpath("//hr[contains(@class,'oxd-divider') and contains(@class,'orangehrm-horizontal-margin')]");
    private By resultTable = By.className("oxd-table-body");

    public VerifyEmployee(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmployeeIdToSearch(String employeeId) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdInput));
        searchInput.clear();
        searchInput.sendKeys(employeeId);
    }

    public void clickSearchButton() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(2000);
    }

    /**
     * Verifies employee by ID and name with simple, concise logging.
     */
    public boolean verifyEmployeeByIdAndName(String employeeId, String expectedFirstName, String expectedLastName) {
        enterEmployeeIdToSearch(employeeId);
        try {
            clickSearchButton();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        try {
            WebElement hr = wait.until(ExpectedConditions.visibilityOfElementLocated(hrLine));
            WebElement resultArea = wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(RelativeLocator.with(resultTable).below(hr))
            ));

            wait.until(driver -> {
                String text = resultArea.getText();
                return text.contains(employeeId) || text.contains("No Records Found");
            });

            String resultText = resultArea.getText();
            System.out.println("\n================= Verification Log =================");
            System.out.println("Expected Employee ID: " + employeeId);
            System.out.println("Expected Name       : " + expectedFirstName + " " + expectedLastName);
            System.out.println("Search Result   : \n" + resultText);

            if (resultText.contains(employeeId)) {
                boolean firstNameMatch = resultText.contains(expectedFirstName);
                boolean lastNameMatch = resultText.contains(expectedLastName);

                if (firstNameMatch && lastNameMatch) {
                    System.out.println("Status              : ✅ Name Verified Successfully");
                    System.out.println("====================================================\n");
                    return true;
                } else {
                    System.out.println("Status              : ❌ Name Mismatch");
                    System.out.println("Found Name Fragment : " + extractFoundName(resultText));
                    System.out.println("====================================================\n");
                    return false;
                }
            }

            if (resultText.contains("No Records Found")) {
                System.out.println("Status              : ❌ No Records Found");
                System.out.println("====================================================\n");
                return false;
            }

            System.out.println("Status              : ⚠️ Unexpected Search Result");
            System.out.println("====================================================\n");
            return false;

        } catch (Exception e) {
            System.out.println("Status              : ❌ Exception - " + e.getMessage());
            System.out.println("====================================================\n");
            return false;
        }
    }

    // Optional helper method to extract found name (basic parsing)
    private String extractFoundName(String resultText) {
        // Naive implementation, assumes first row is relevant
        String[] lines = resultText.split("\\n");
        for (String line : lines) {
            if (line.contains("Employee") || line.contains("Id")) continue;
            if (line.trim().isEmpty()) continue;
            return line.trim();
        }
        return "Not Found";
    }

}
