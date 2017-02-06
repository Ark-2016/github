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
public class MainPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(MainPage.class);
    private final String URL = "https://github.com/";

    @FindBy(xpath = "//a[contains(@aria-label, 'Create new')]")
    private WebElement createNewButton;
    @FindBy(xpath = "//a[contains(text(), 'New repository')]")
    private WebElement newRepositoryLink;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        if(newRepoButtonVisible()){
            logger.info("Main page open");
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean newRepoButtonVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(createNewButton));
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

    public boolean transitionToNewRepositoryPage() {
        newRepoButtonVisible();
        createNewButton.click();
        newRepositoryLink.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.titleIs("Create a New Repository"));
            logger.info("Transition to the New Repository Page");
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

}
