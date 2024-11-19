package ru.yandex.practicum.scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//Первая форма заказа самоката
public class FirstOrderPage extends OrderBasePage {

    @FindBy(className = "Order_Header__BZXOb")
    private WebElement header; //Заголовок окна
    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement nameInput; //Поле Имя
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement surnameInput; //Поле Фамилия
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput; //Поле Адрес
    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput; //Поле Телефон

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']")
    private WebElement submitButton; //Кнопка Далее

    //Локаторы для станции метро
    private final String metroStationButtonXpath = ".//input[@placeholder='* Станция метро']/parent::div";
    private final String metroStationDropdownXpath = ".//div[@class='select-search has-focus']/div[@class='select-search__select']/ul";
    private final String metroStationItemXpath = "/li";

    public FirstOrderPage(WebDriver driver, OrderBasePage nextPage) {
        super(driver, nextPage);
    }

    //Выбор станции метро по индексу
    private void selectMetroStation(int metroStationIndex) {
        if (metroStationIndex >= 0) {
            driver.findElement(By.xpath(metroStationButtonXpath)).click();
            By stationListLocator = By.xpath(metroStationDropdownXpath);
            new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(stationListLocator));
            List<WebElement> items = driver.findElements(By.xpath(metroStationDropdownXpath.concat(metroStationItemXpath)));
            if (!items.isEmpty()) {
                items.get(metroStationIndex).click();
            }
        }
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

    //Нажатие на кнопку Далее
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
