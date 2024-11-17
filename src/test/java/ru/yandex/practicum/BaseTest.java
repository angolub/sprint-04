package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected BROWSER browser = BROWSER.CHROME;
    protected final String scooterURL = "https://qa-scooter.praktikum-services.ru/";

    protected void initWebDriver() {
        if (browser == BROWSER.CHROME) {
            driver = new ChromeDriver();
        }
        if (browser == BROWSER.FIREFOX) {
            driver = new FirefoxDriver();
        }
        if (browser == BROWSER.SAFARI) {
            driver = new SafariDriver();
        }
        if (browser == BROWSER.EDGE) {
            driver = new EdgeDriver();
        }
        driver.get(scooterURL);
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

