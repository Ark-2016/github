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
 * Created by Ark on 06.02.2017.
 */
public class NewRepoPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(NewRepoPage.class);
    private final String URL = "https://github.com/new/";

    @FindBy(id="repository_name")
    WebElement repoNameField;
    @FindBy(id="repository_auto_init")
    WebElement initRepoChechBox;
    @FindBy(css=".btn.btn-primary.first-in-line")
    WebElement createRepoButton;

    public NewRepoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        logger.info("NewRepo page open");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean newRepoButtonVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(repoNameField));
            wait.until(ExpectedConditions.visibilityOf(initRepoChechBox));
            wait.until(ExpectedConditions.visibilityOf(createRepoButton));
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

    public boolean createNewRepo(String name) {
        newRepoButtonVisible();
        repoNameField.sendKeys(name);
        initRepoChechBox.click();
        createRepoButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.titleIs("Ark-2016/" + name));
            logger.info("New Repository created: " + name);
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

}
