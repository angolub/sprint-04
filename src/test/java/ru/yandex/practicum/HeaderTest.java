package ru.yandex.practicum;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.scooter.HeaderSection;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeaderTest extends BaseTest {
    //Проверяет ссылку на домашнюю страницу
    @Test
    public void checkMainPageClick() {
        initWebDriver();

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
}
