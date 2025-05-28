package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath("//span[text()='PIM']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverAndClickPIM() {
        WebElement pimElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pimMenu));

        Actions actions = new Actions(driver);
        actions.moveToElement(pimElement).click().perform();
    }
}
