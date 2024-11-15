package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FirstOrderBasePage extends OrderBasePage {

    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement nameInput;
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement surnameInput;
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput;
    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput;

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']")
    private WebElement submitButton;

    private final String metroStationButtonXpath = ".//input[@placeholder='* Станция метро']/parent::div";
    private final String metroStationDropdownXpath = ".//div[@class='select-search__select']";
    private final String metroStationListXpath = "/ul";
    private final String metroStationItemXpath = "/li";

    public FirstOrderBasePage(WebDriver driver) {
        super(driver);
    }

    private void selectMetroStation(int metroStationIndex) {
        driver.findElement(By.xpath(metroStationButtonXpath)).click();
        By stationListLocator = By.xpath(metroStationDropdownXpath.concat(metroStationListXpath));
        new WebDriverWait(driver, 6).until(ExpectedConditions.visibilityOfElementLocated(stationListLocator));
        List<WebElement> items = driver.findElements(By.xpath(metroStationDropdownXpath.concat(metroStationListXpath).concat(metroStationItemXpath)));
        items.get(metroStationIndex).click();
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
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(metroStationButtonXpath)));

        nameInput.clear();
        surnameInput.clear();
        addressInput.clear();
        phoneInput.clear();

        nameInput.sendKeys(name);
        surnameInput.sendKeys(surname);
        addressInput.sendKeys(address);
        phoneInput.sendKeys(phone);

        selectMetroStation(metroStationIndex);
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
        SecondOrderBasePage page = new SecondOrderBasePage(driver);
        return page.makeOrder(name, surname, address, phone, metroStationIndex, deliveryDay, rentalPeriodIndex, color, comment);
    }
}
