package ru.yandex.practicum.scooter;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.practicum.scooter.order.*;

//Домашняя страница сервиса Самокат
public class MainPage  extends BasePage{
    @FindBy(xpath=".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement middleOrderButton; //Кнопка Заказать в срередине страницы

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        CookieSection cookieSection = new CookieSection(driver);
        cookieSection.acceptCookie();
    }

    //Создает цепочку форм заказа
    private OrderBasePage initChainOrderForm() {
        return new FirstOrderPage(driver,
                new SecondOrderPage(driver,
                        new ConfirmOrderModalPage(driver,
                                new SuccessOrderModalPage(driver, null))));
    }

    //Запускает процесс оформления успешного заказа
    private String makeOrder(
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment
    ) {
        OrderBasePage page = initChainOrderForm();
        return page.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

    //Запускает процесс оформления успешного заказа по кнопке Заказать из хидера
    public String makeOrderFromHeader(
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment
    ) {
        HeaderSection header = new HeaderSection(driver);
        header.clickOrderButton();
        return makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

    //Запускает процесс оформления успешного заказа по кнопке Заказать с середины страницы
    public String makeOrderFromMiddle(
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment
    ) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", middleOrderButton);
        middleOrderButton.click();
        return makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

    //Запускает процесс проверки валидации форм заказа
    public String checkValidationOrderForm(
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment,
            int deep
    ) {
        HeaderSection header = new HeaderSection(driver);
        header.clickOrderButton();

        OrderBasePage page = initChainOrderForm();
        return page.makeInvalidOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment, deep);
    }
}
