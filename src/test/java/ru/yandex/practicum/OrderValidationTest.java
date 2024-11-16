package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.scooter.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderValidationTest {
    private WebDriver driver;
    private final String scooterURL = "https://qa-scooter.praktikum-services.ru/";

    private final int deep;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final int metroStationIndex;
    private final  int deliveryDay;
    private final int rentalPeriodIndex;
    private final String color;
    private final String comment;

    public OrderValidationTest(
            int deep,
            String name,
            String surname,
            String address,
            String phone,
            int metroStationIndex,
            int deliveryDay,
            int rentalPeriodIndex,
            String color,
            String comment
    ){
        this.deep = deep;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.metroStationIndex = metroStationIndex;
        this.deliveryDay = deliveryDay;
        this.rentalPeriodIndex = rentalPeriodIndex;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                //Проверка поля Имя
                {
                        0,
                        "", //Ошибка поле незаполнено
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "А", //Ошибка короткое значение
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "Алина*", //Ошибка некорректный знак
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "АлинаZ", //Ошибка некорректная буква
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "ДлинныйдлинныйДл", //Ошибка длинное имя (ma[length = 15)
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поля Фамилия
                {
                        0,
                        "Алина",
                        "", //Ошибка поле незаполнено
                        "Санкт-Петербург",
                        "11111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "Алина",
                        "Г", //Ошибка короткое значение
                        "Санкт-Петербург",
                        "11111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "Алина",
                        "ГолубьZ", //Ошибка некорректная буква
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "Алина",
                        "ДлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйДл", //Ошибка длинное значение (ma[length = 64)
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поля Адрес
                {
                        0,
                        "Алина",
                        "Голубь",
                        "",//Ошибка поле незаполнено
                        "111111111114",
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поля Телефон
                {
                        0,
                        "Алина",
                        "Голубь",
                        "Санкт-Петербург",
                        "", //Ошибка поле незаполнено
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                {
                        0,
                        "Алина",
                        "Голубь",
                        "Санкт-Петербург",
                        "1111111111", //Ошибка короткое значение
                        100,
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поле Станция метро
                {
                        0,
                        "Алина",
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        -1, //Ошибка поле незаполнено
                        14,
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поле Дата доставки
                {
                        1,
                        "Алина",
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        0, //Ошибка поле незаполено
                        3,
                        "grey",
                        "Текст комментария"
                },
                //Проверка поле Срок аренды
                {
                        1,
                        "Алина",
                        "Голубь",
                        "Санкт-Петербург",
                        "111111111114",
                        100,
                        14,
                        -1, //Ошибка поле незаполено
                        "grey",
                        "Текст комментария"
                }
        };
    }

    //Возвоащает ожидаемый хидер в зависимости от глубины прохождения по формам заказа
    private String getExpectedHeader(int deep) {
        if (deep == 0) {
            return "Для кого самокат";
        }
        if (deep == 1) {
            return "Про аренду";
        }
        return null;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    //Проверяет валидацию форм заказа
    @Test
    public void checkOrderValidation() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        String actual = mainPage.checkValidationOrderForm(
                name,
                surname,
                address,
                phone,
                metroStationIndex,
                deliveryDay,
                rentalPeriodIndex,
                color,
                comment,
                deep
        );
        String expected = getExpectedHeader(deep);
        assertEquals(expected, actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
