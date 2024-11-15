package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.practicum.scooter.BasePage;

public abstract class OrderBasePage extends BasePage {

    public OrderBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Заполнение формы данными
    protected abstract void fillForm(String name,
                                     String surname,
                                     String address,
                                     String phone,
                                     int metroStationIndex,
                                     int deliveryDay,
                                     int rentalPeriodIndex,
                                     String color,
                                     String comment);

    //Нажатие на кнопку подствеждения формы
    protected abstract void clickSubmitButton();

    //Переход к следующему шагу
    protected abstract String goToNextStep(String name,
                                     String surname,
                                     String address,
                                     String phone,
                                     int metroStationIndex,
                                     int deliveryDay,
                                     int rentalPeriodIndex,
                                     String color,
                                     String comment);

    public  String makeOrder(
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
        fillForm(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
        clickSubmitButton();
        return goToNextStep(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }
}
