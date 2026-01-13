package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    private WebDriver webDriver; //интерфейс описывает любой браузер
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();//идет в инет и скачивает атуальую версию браузера, стягиваем исп файл
        webDriver = new ChromeDriver();//здесь инициализируем конкретный вебдрайвер для хром
        webDriver.manage().window().maximize();//сдлали на вест екран браузер
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//установили задержку на 5 сек
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);

    }

    @After
    public void tearDown() {
        webDriver.quit();//метод квит закрывает брацзер и уничтожает инф из памяти, клоз просто визуально заур вкладку
        logger.info("Browser was closed");
    }
}
