package by.epam.github.logic;

import by.epam.github.driver.DriverSingleton;
import by.epam.github.pages.LoginPage;
import by.epam.github.pages.MainPage;
import by.epam.github.pages.NewRepoPage;
import org.openqa.selenium.WebDriver;

import java.util.Random;

/**
 * Created by Ark on 06.02.2017.
 */
public class Runner {
    private WebDriver webDriver;

    public void openBrowser() {
        webDriver = DriverSingleton.getDriver();
    }

    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }

    public boolean loginGitHub(String userName, String password) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open();
        return loginPage.fillInFormAndSubmit(userName, password);
    }

    public boolean createNewRepository() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        mainPage.transitionToNewRepositoryPage();
        NewRepoPage newRepoPage = new NewRepoPage(webDriver);
        Random rnd = new Random();
        String name = "Repo" + rnd.nextInt(10000);
        return newRepoPage.createNewRepo(name );
    }
}
