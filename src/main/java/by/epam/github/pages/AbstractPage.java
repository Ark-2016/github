package by.epam.github.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ark on 05.02.2017.
 */
abstract class AbstractPage {
    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    protected abstract void open();
}
