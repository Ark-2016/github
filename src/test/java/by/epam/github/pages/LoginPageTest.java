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
public class LoginPageTest {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private final String PAGE_TITLE = "Sign in to GitHub · GitHub";
    private final String LOGIN = "Ark-2016";
    private final String PASSWORD = "parol_Ark-2016";

    @BeforeClass
    public void setUp() throws Exception {
        webDriver = DriverSingleton.getDriver();
        loginPage = new LoginPage(webDriver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test
    public void testOpen() throws Exception {
        loginPage.open();
        assertEquals(loginPage.getTitle(), PAGE_TITLE,"Wrong Login page title");
    }

    @Test(dependsOnMethods = "testOpen")
    public void testFillInFormAndSubmit() throws Exception {
        assertTrue(loginPage.loginFormVisible(), "LoginForm invisible");
        loginPage.fillInFormAndSubmit(LOGIN, PASSWORD);
    }

}