package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Модальное окно подтверждения заказа
public class ConfirmOrderModalPage extends OrderBasePage {

    @FindBy(xpath=".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement header; //Заголовок окна

    @FindBy(xpath=".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")
    private WebElement submitButton; //Кнопка подтверждения

    public ConfirmOrderModalPage(WebDriver driver, OrderBasePage nextPage) {
        super(driver, nextPage);
    }

    //Подтверждение заказа
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
