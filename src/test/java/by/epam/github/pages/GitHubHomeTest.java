package by.epam.github.pages;

import by.epam.github.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Ark on 05.02.2017.
 */
public class GitHubHomeTest {
    private WebDriver webDriver;
    private GitHubHome gitHubHomePage;
    private final String PAGE_TITLE = "How people build software · GitHub";

    @BeforeClass
    public void setUp() throws Exception {
        webDriver = DriverSingleton.getDriver();
        gitHubHomePage = new GitHubHome(webDriver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test
    public void testOpen() throws Exception {
        gitHubHomePage.open();
        assertEquals(gitHubHomePage.getTitle(), PAGE_TITLE,"Wrong Home page title");
    }

    @Test(dependsOnMethods = "testOpen")
    public void testMoveToLoginPage() throws Exception {
        assertTrue(gitHubHomePage.loginButtonVisible(), "LoginButton invisible");
        gitHubHomePage.moveToLoginPage();
    }

}