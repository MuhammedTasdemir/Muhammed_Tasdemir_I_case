package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QAJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By seeAllJobsBtn = By.xpath("//a[contains(text(),'See all QA jobs')]");
    private By departmentDropdown = By.id("select2-filter-by-department-container");
    private By locationDropdown = By.id("select2-filter-by-location-container");
    private By dropdownOptions = By.cssSelector("ul.select2-results__options li");

    private By jobsList = By.cssSelector("#jobs-list");
    private By jobCard = By.cssSelector(".position-list-item.col-12.col-lg-4.qualityassurance.istanbul-turkiye.full-timeremote");

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Click "See All QA Jobs"
    public void clickSeeAllJobs() {
        wait.until(ExpectedConditions.elementToBeClickable(seeAllJobsBtn)).click();
    }

    // Wait until department dropdown shows the required department
    private void waitForDepartmentLoaded(String departmentName) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(departmentDropdown, departmentName));
    }

    // Select from a dropdown by visible text
    private void selectFromDropdown(By dropdown, String value) {
        driver.findElement(dropdown).click();
        List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));

        boolean found = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                option.click();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Option not found in dropdown: " + value);
        }
    }

    // Filter jobs by location and department
    public void filterJobs(String location, String department) {
        waitForDepartmentLoaded(department);
        selectFromDropdown(locationDropdown, location);
        selectFromDropdown(departmentDropdown, department);
    }

    // Scroll to jobs list container
    public void scrollToJobsList() {
        WebElement jobsListContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(jobsList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", jobsListContainer);
    }

    // Hover over the job card
    public void hoverOverJobCard() {
        WebElement job = wait.until(ExpectedConditions.visibilityOfElementLocated(jobCard));
        Actions actions = new Actions(driver);
        actions.moveToElement(job).perform();
    }

    // Click "View Role" from job card
    public void clickViewRole() {
        WebElement job = wait.until(ExpectedConditions.visibilityOfElementLocated(jobCard));
        WebElement viewBtn = job.findElement(By.xpath(".//a[contains(text(),'View Role')]"));
        wait.until(ExpectedConditions.elementToBeClickable(viewBtn)).click();
    }

    // Get all jobs (optional, if you need to verify multiple jobs)
    public List<WebElement> getJobs() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".position-list-item")));
    }
}
