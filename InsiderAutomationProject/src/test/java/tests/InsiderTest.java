package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CareersPage;
import pages.HomePage;
import pages.QAJobsPage;

public class InsiderTest extends BaseTest {

    @Test
    public void testInsiderCareersFlow() {
        // Step 1: Open homepage
        driver.get("https://useinsider.com/");
        HomePage home = new HomePage(driver);
        home.acceptCookies(); // click accept cookies
        Assert.assertTrue(home.getHomePageTitle().contains("Insider"), "Home page not opened!");
        System.out.println("Step 1: Open Insider homepage");

        // Step 2: Go to Careers page via menu
        home.goToCareers();
        CareersPage careers = new CareersPage(driver);
        Assert.assertTrue(careers.isLocationsDisplayed(), "Locations block not visible!");
        Assert.assertTrue(careers.isTeamsDisplayed(), "Teams block not visible!");
        Assert.assertTrue(careers.isLifeAtInsiderDisplayed(), "Life at Insider block not visible!");
        System.out.println("Step 2: Navigate to Careers page");

        // Step 3: Open QA Jobs page directly
        driver.get("https://useinsider.com/careers/quality-assurance/");
        QAJobsPage qaPage = new QAJobsPage(driver);

        // Click "See All QA Jobs" and filter
        qaPage.clickSeeAllJobs();
        qaPage.filterJobs("Istanbul, Turkiye", "Quality Assurance");
        System.out.println("Step 3: QA jobs filtered");

        // Step 4: Scroll to jobs list and hover/click job card
        qaPage.scrollToJobsList();
        qaPage.hoverOverJobCard();
        qaPage.clickViewRole();

        // Step 5: Switch to new tab (Lever application) and verify
        String originalWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("lever.co"));
        Assert.assertTrue(driver.getCurrentUrl().contains("lever.co"), "Not redirected to Lever application form!");
        System.out.println("Step 5: Redirect verified on Lever page");
    }
}
