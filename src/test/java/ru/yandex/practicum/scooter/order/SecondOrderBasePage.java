package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SecondOrderBasePage extends OrderBasePage {

    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement deliveryDayInput;
    @FindBy(xpath = ".//div[@class='Dropdown-root']")
    private WebElement rentalPeriodButton;
    @FindBy(id = "black")
    private WebElement blackColorCheckbox;
    @FindBy(id = "grey")
    private WebElement greyColorCheckbox;
    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput;

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement submitButton;

    private final String deliveryDayButtonXpath = ".//div[contains(@class, 'react-datepicker__day react-datepicker__day--0%02d')]";
    private final String rentalPeriodListXpath = ".//div[@class='Dropdown-root is-open']/div[@class='Dropdown-menu']";
    private final String rentalPeriodItemXpath = "/div";

    public SecondOrderBasePage(WebDriver driver) {
        super(driver);
        // PageFactory.initElements(driver, this);
    }

    private void setDeliveryDay(int deliveryDay) {
        deliveryDayInput.click();
        driver.findElement(By.xpath(String.format(deliveryDayButtonXpath, deliveryDay))).click();
    }

    private void selectRentalPeriod(int rentalPeriodIndex) {
        rentalPeriodButton.click();
        List<WebElement> items = driver.findElements(By.xpath(rentalPeriodListXpath.concat(rentalPeriodItemXpath)));
        items.get(rentalPeriodIndex).click();
    }

    private void setColor(String color) {
        if (color == "grey") greyColorCheckbox.click();
        if (color == "black") blackColorCheckbox.click();
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
                            String comment) {
        deliveryDayInput.clear();
        commentInput.clear();

        setDeliveryDay(deliveryDay);
        selectRentalPeriod(rentalPeriodIndex);
        setColor(color);
        commentInput.sendKeys(comment);
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
                                  String comment) {
        ConfirmOrderModalBasePage page = new ConfirmOrderModalBasePage(driver);
        return page.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

}
