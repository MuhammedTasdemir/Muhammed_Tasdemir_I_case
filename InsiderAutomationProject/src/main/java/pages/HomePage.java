package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By companyMenu = By.xpath("(//a[normalize-space()='Company'])[1]");
    private By careersPage = By.xpath("//a[normalize-space()='Careers']");
    private By acceptCookiesBtn = By.id("wt-cli-accept-all-btn");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
            System.out.println("Cookies accepted successfully.");
        } catch (Exception e) {
            System.out.println("No cookies banner found.");
        }
    }

    public void goToCareers() {
        wait.until(ExpectedConditions.elementToBeClickable(companyMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(careersPage)).click();
    }
}
