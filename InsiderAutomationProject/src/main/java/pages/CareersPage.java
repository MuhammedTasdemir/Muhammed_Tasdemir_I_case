package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CareersPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By locationsBlock = By.cssSelector(".elementor-element-8ab30be");
    private By teamsBlock = By.cssSelector(".elementor-element-b6c45b2");
    private By lifeAtInsiderBlock = By.cssSelector(".elementor-element-a8e7b90");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLocationsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locationsBlock)).isDisplayed();
    }

    public boolean isTeamsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(teamsBlock)).isDisplayed();
    }

    public boolean isLifeAtInsiderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lifeAtInsiderBlock)).isDisplayed();
    }
}
