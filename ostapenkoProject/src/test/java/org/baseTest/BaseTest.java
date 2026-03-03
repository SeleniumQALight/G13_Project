package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.pages.PageProvider;
import org.utils.ConfigProvider;
import org.utils.ScreenShot;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    @Before
    public void setup() {
        logger.info("----------------------" + testName.getMethodName() + " was started" + "----------------------");
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
        initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT()));
        logger.info("Browser was opened");

        pageProvider = new PageProvider(webDriver);
    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver() {
        String browserFromProperty = System.getProperty("browser");
        if (browserFromProperty == null) {
            logger.info("Browser from Property is null. Will be used Chrome");
            browserFromProperty = "chrome";
        } else {
            logger.info("Browser from Property is: " + browserFromProperty);
        }

        if ((browserFromProperty.equalsIgnoreCase("chrome"))) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            logger.info("Browser is chrome");
        } else if (browserFromProperty.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browserFromProperty.toLowerCase())) {
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }

        return webDriver;
    }

    @After
    public void tearDown() {
//        webDriver.quit();
//        logger.info("Browser was closed");
        logger.info("----------------------" + testName.getMethodName() + " was finished" + "----------------------");
    }

    @Rule()
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };

    protected void takeScreenshot(String screenShotName) {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(screenShotName, screen));
    }

}
