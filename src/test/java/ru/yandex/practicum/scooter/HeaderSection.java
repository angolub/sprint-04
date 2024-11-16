package ru.yandex.practicum.scooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//Секция заголовка окон
public class HeaderSection extends BasePage{
    @FindBy(className = "Button_Button__ra12g")
    private WebElement orderButton;  //Кнопка Заказать самокат

    @FindBy(className = "Header_LogoScooter__3lsAR")
    private WebElement mainPageLink;  //Ссылка на домашнюю страницу

    @FindBy(className = "Header_LogoYandex__3TSOI")
    private WebElement yandexLink;   //Ссылка на страницу Яндекс

    public HeaderSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOrderButton() {
        orderButton.click();;
    }

    public void clickMainPageLink() {
        mainPageLink.click();;
    }

    public void clickYandexLink() {
        yandexLink.click();;
    }
}
