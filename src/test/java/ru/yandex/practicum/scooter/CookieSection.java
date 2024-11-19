package ru.yandex.practicum.scooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Секция предупреждения о куки
public class CookieSection extends BasePage{
    @FindBy(xpath=".//button[@class='App_CookieButton__3cvqF']")
    private WebElement cookieButton; //Кнопка согласия с куки

    public CookieSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Подтверждение использования куки
    public void acceptCookie() {
        cookieButton.click();;
    }
}
