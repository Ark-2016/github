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
public class LoginPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(LoginPage.class);
    private final String URL = "https://github.com/login";

//    private final String LOGIN = "Ark-2016";
//    private final String PASSWORD = "parol_Ark-2016";

    @FindBy(id="login_field")
    private WebElement loginField;
    @FindBy(id="password")
    private WebElement passwordField;
    @FindBy(css=".btn.btn-primary.btn-block")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        logger.info("Login page open");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean loginFormVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(loginField));
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            wait.until(ExpectedConditions.visibilityOf(submitButton));
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

    public boolean fillInFormAndSubmit(String userName, String password) {
        loginFormVisible();
        loginField.sendKeys(userName);
        passwordField.sendKeys(password);
        submitButton.submit();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.titleIs("GitHub"));
            return true;
        } catch(WebDriverException e) {
            return false;
        }
    }

}
