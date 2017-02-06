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
public class MainPageTest {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final String PAGE_TITLE = "GitHub";
    private final String LOGIN = "Ark-2016";
    private final String PASSWORD = "parol_Ark-2016";

    @BeforeClass
    public void setUp() throws Exception {
        webDriver = DriverSingleton.getDriver();
        loginPage = new LoginPage(webDriver);
        loginPage.open();
        loginPage.fillInFormAndSubmit(LOGIN, PASSWORD);
        mainPage = new MainPage(webDriver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test
    public void testOpen() throws Exception {
        mainPage.open();
        assertEquals(mainPage.getTitle(), PAGE_TITLE,"Wrong Main page title");
    }

    @Test(dependsOnMethods = "testOpen")
    public void testOpenNewRepositoryPage() throws Exception {
        assertTrue(mainPage.newRepoButtonVisible(), "newRepoButton invisible");
        mainPage.transitionToNewRepositoryPage();
    }

}