package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.practicum.scooter.BasePage;

//Базовый класс для форм заказа
public abstract class OrderBasePage extends BasePage {
    protected OrderBasePage nextPage; //Ссылка на следующую страницу заказа

    public OrderBasePage(WebDriver driver, OrderBasePage nextPage) {
        super(driver);
        this.nextPage = nextPage;
        PageFactory.initElements(driver, this);
    }

    //Заполнение формы данными
    protected void fillForm(String name,
                                     String surname,
                                     String address,
                                     String phone,
                                     int metroStationIndex,
                                     int deliveryDay,
                                     int rentalPeriodIndex,
                                     String color,
                                     String comment){

    }

    //Нажатие на кнопку подствеждения формы
    protected abstract void clickSubmitButton();

    //Получение заголовка окна
    protected abstract String getHeaderText();

    //Проходит шаги оформления успешного заказа на странице
    //   Заполнение формы
    //   Нажатие кнопки продолжения
    //   Если есть следующая форма, то передать управление ей. Если нет следующей формы вернуть заголовок своей формы.
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

        if (nextPage != null) {
            return nextPage.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
        }
        return getHeaderText();
    }

    //Проходит шаги проверки валидации формы заказа
    //   Заполнение формы
    //   Нажатие кнопки продолжения
    //   Если на проверяемой форме, то возвращаем Заголовок формы. Если нет, то уменьшаем глубину и передать управление следующей форме.
    public  String makeInvalidOrder(
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment,
            int deep     //Глубина прохода по формам. На какой форме оформление заказа должно остановиться.
    ) {
        fillForm(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
        clickSubmitButton();
        if (deep > 0 && nextPage != null) {
            deep--;
            return nextPage.makeInvalidOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment, deep);
        }
        return getHeaderText();
    }
}
