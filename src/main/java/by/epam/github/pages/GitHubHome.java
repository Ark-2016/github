package by.epam.github.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ark on 05.02.2017.
 */
public class GitHubHome extends AbstractPage {
    private static Logger logger = LogManager.getLogger(GitHubHome.class);
    private final String URL = "https://github.com/";

    @FindBy(css="a[href='/login']")
    private WebElement loginButton;

    public GitHubHome(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected void open() {
        driver.navigate().to(URL);
        logger.info("Home page open");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean loginButtonVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

    public void moveToLoginPage() {
        loginButton.click();
    }
}
