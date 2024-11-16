package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.scooter.FAQSection;
import ru.yandex.practicum.scooter.HeaderSection;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeaderTest {
    private WebDriver driver;
    private final String scooterURL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    //Проверяет ссылку на домашнюю страницу
    @Test
    public void checkMainPageClick() {
        driver = new ChromeDriver();
        driver.get(scooterURL);

        HeaderSection header = new HeaderSection(driver);
        header.clickOrderButton();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("order"));

        header.clickMainPageLink();
        String actualURL = driver.getCurrentUrl();

        assertEquals("Ссылка на главную страницу должна переходить", scooterURL, actualURL);
    }

    //Проверяет ссылку на страницу Яндекс
    @Test
    public void checkYandexPageClick() {
        driver = new ChromeDriver();
        driver.get(scooterURL);

        HeaderSection header = new HeaderSection(driver);
        header.clickYandexLink();

        String expectedURL = "https://ya.ru/";

        Object[] windowHandles = driver.getWindowHandles().toArray();
        assertTrue(windowHandles.length > 1);
        driver.switchTo().window((String) windowHandles[1]);
        String actualURL = driver.getCurrentUrl();
        assertTrue("Ссылка должна переходить на страницу Яндекса", actualURL.contains(expectedURL));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
