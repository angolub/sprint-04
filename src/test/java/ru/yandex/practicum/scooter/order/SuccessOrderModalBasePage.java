package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessOrderModalBasePage extends OrderBasePage {
    @FindBy(xpath=".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement header;

    public SuccessOrderModalBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        return header.getText().strip();
    }
}
