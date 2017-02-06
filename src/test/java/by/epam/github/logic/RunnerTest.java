package by.epam.github.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by Ark on 06.02.2017.
 */
public class RunnerTest {
    private static Logger logger = LogManager.getLogger(RunnerTest.class);
    private final String LOGIN = "Ark-2016";
    private final String PASSWORD = "parol_Ark-2016";

    private Runner runner;

    @BeforeClass
    public void setUp() throws Exception {
        runner = new Runner();
        runner.openBrowser();
    }

    @AfterClass
    public void tearDown() throws Exception {
        runner.closeBrowser();
    }

    @Test
    public void testLoginGitHub() {
        assertTrue(runner.loginGitHub(LOGIN, PASSWORD), "loginGitHub failed");
        logger.info("loginGitHub done");
    }

    @Test(dependsOnMethods = "testLoginGitHub")
    public void testCreateNewRepository() {
        assertTrue(runner.createNewRepository(), "createNewRepository failed");
        logger.info("createNewRepository done");
    }
}