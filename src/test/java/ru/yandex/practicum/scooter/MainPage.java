package ru.yandex.practicum.scooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.practicum.scooter.order.FirstOrderBasePage;

public class MainPage  extends BasePage{
    @FindBy(className = "Button_Button__ra12g")
    private WebElement headerOrderButton;

    @FindBy(xpath=".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement middleOrderButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

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
        FirstOrderBasePage page = new FirstOrderBasePage(driver);
        return page.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

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
        headerOrderButton.click();
        return makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }

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
}
