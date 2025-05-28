package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {
    public static WebDriver driver;

    public void setup() {
        ConfigReader.loadConfig();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

