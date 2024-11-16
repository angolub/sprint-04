package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Модальное окно с сообщением об успешном заказе
public class SuccessOrderModalPage extends OrderBasePage {
    @FindBy(xpath=".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement header;

    public SuccessOrderModalPage(WebDriver driver, OrderBasePage nextPage) {
        super(driver, nextPage);
    }

    @Override
    protected void clickSubmitButton() {
    }

    //Получение заголовка окна
    @Override
    protected String getHeaderText() {
        return header.getText().strip();
    }
}
