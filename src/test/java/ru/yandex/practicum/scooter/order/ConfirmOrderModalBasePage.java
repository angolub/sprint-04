package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmOrderModalBasePage extends OrderBasePage {

    private final String expectedHeader = "Хотите оформить заказ?";
    @FindBy(xpath=".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement header;

    @FindBy(xpath=".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")
    private WebElement submitButton;

    public ConfirmOrderModalBasePage(WebDriver driver) {
        super(driver);
    }

    @Override
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

    @Override
    protected void clickSubmitButton() {
        submitButton.click();
    }

    @Override
    protected String goToNextStep(String name,
                                           String surname,
                                           String address,
                                           String phone,
                                           int metroStationIndex,
                                           int deliveryDay,
                                           int rentalPeriodIndex,
                                           String color,
                                           String comment){

        SuccessOrderModalBasePage page = new SuccessOrderModalBasePage(driver);
        return page.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }
}
