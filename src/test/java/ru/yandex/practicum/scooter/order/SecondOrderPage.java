package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
//Вторая форма заказа самоката
public class SecondOrderPage extends OrderBasePage {
    @FindBy(className = "Order_Header__BZXOb")
    private WebElement header; //Заголовок окна
    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement deliveryDayInput; //Поле Когда привезти
    @FindBy(xpath = ".//div[@class='Dropdown-root']")
    private WebElement rentalPeriodButton; //Поле срок аренды
    @FindBy(id = "black")
    private WebElement blackColorCheckbox; //Поле черный цвет
    @FindBy(id = "grey")
    private WebElement greyColorCheckbox; //Поле серый цвет
    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput; //Поле комментарий

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement submitButton; //Кнопка заказать

    //Локатор для поля Когда привезти
    private final String deliveryDayButtonXpath = ".//div[contains(@class, 'react-datepicker__day react-datepicker__day--0%02d')]";
    //Локатор для поля срок аренды
    private final String rentalPeriodListXpath = ".//div[@class='Dropdown-root is-open']/div[@class='Dropdown-menu']";
    private final String rentalPeriodItemXpath = "/div";

    public SecondOrderPage(WebDriver driver, OrderBasePage nextPage) {
        super(driver, nextPage);
    }

    //Установить дату когда привезти заказ
    //deliveryDay - число месяца
    private void setDeliveryDay(int deliveryDay) {
        if (deliveryDay > 0) {
            deliveryDayInput.click();
            driver.findElement(By.xpath(String.format(deliveryDayButtonXpath, deliveryDay))).click();
        }
    }

    //Выбрать Срок аренды
    private void selectRentalPeriod(int rentalPeriodIndex) {
        if (rentalPeriodIndex >= 0) {
            rentalPeriodButton.click();
            List<WebElement> items = driver.findElements(By.xpath(rentalPeriodListXpath.concat(rentalPeriodItemXpath)));
            items.get(rentalPeriodIndex).click();
        }
    }

    //Установить цвет
    private void setColor(String color) {
        if (color == "grey") greyColorCheckbox.click();
        if (color == "black") blackColorCheckbox.click();
    }

    //Заполнение формы
    @Override
    protected void fillForm(String name,
                            String surname,
                            String address,
                            String phone,
                            int metroStationIndex,
                            int deliveryDay,
                            int rentalPeriodIndex,
                            String color,
                            String comment) {
        deliveryDayInput.clear();
        commentInput.clear();

        setDeliveryDay(deliveryDay);
        selectRentalPeriod(rentalPeriodIndex);
        setColor(color);
        commentInput.sendKeys(comment);
    }

    //Нажатие на кнопку Заказать
    @Override
    protected void clickSubmitButton() {
        submitButton.click();
    }

    //Получение заголовка окна
    @Override
    protected String getHeaderText() {
        return header.getText();
    }
}
