package by.epam.github.pages;

import by.epam.github.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by Ark on 06.02.2017.
 */
public class NewRepoPageTest {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private NewRepoPage newRepoPage;
    private final String PAGE_TITLE = "Create a New Repository";
    private final String LOGIN = "Ark-2016";
    private final String PASSWORD = "parol_Ark-2016";

    @BeforeClass
    public void setUp() throws Exception {
        webDriver = DriverSingleton.getDriver();
        loginPage = new LoginPage(webDriver);
        loginPage.open();
        loginPage.fillInFormAndSubmit(LOGIN, PASSWORD);
        mainPage = new MainPage(webDriver);
        mainPage.open();
        mainPage.transitionToNewRepositoryPage();
        newRepoPage = new NewRepoPage(webDriver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test
    public void testOpen() throws Exception {
//        newRepoPage.open();
        assertEquals(newRepoPage.getTitle(), PAGE_TITLE, "Wrong NewRepo page title");
    }

    @Test(dependsOnMethods = "testOpen")
    public void testCreateNewRepo() throws Exception {
        Random rnd = new Random();
        String name = "Repo" + rnd.nextInt(10000);
        newRepoPage.createNewRepo(name );
    }

}