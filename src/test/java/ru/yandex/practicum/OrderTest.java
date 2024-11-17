package ru.yandex.practicum;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import ru.yandex.practicum.scooter.MainPage;

@RunWith(Parameterized.class)
public class OrderTest extends  BaseTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final int metroStationIndex;
    private final  int deliveryDay;
    private final int rentalPeriodIndex;
    private final String color;
    private final String comment;
    private final String expectedResult = "Заказ оформлен";

    public OrderTest(
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
                {
                        "Алина",
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
                        "Ян",
                        "Ки",
                        "Дубна",
                        "11111111111",
                        0,
                        1,
                        0,
                        "black",
                        ""
                },
                {
                        "ДлинныйдлинныйД",
                        "ДлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйД",
                        "Длинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинный",
                        "1111111111144",
                        224,
                        30,
                        6,
                        "black",
                        "ДлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйДлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинный"
                },
                {
                        "Длина Спробелом",
                        "Длинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинный",
                        "Длинный Спробелом длинныйдлинныйдлинныйдлинныйдли",
                        "0111111111144",
                        220,
                        24,
                        4,
                        "grey",
                        "Длинныйдлинный длинный*длин ?/ныйдл+=инныйдлинныйдлинныйдлинныйдлинныйДлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинный"
                }
        };
    }

    //Проверяет положительный сценарий заказа с кнопки в хидере
    @Test
    public void checkOrderWorkflowFromHeader() {
        // драйвер для браузера Chrome
        initWebDriver();

        MainPage mainPage = new MainPage(driver);
        String actual = mainPage.makeOrderFromHeader(
                name,
                surname,
                address,
                phone,
                metroStationIndex,
                deliveryDay,
                rentalPeriodIndex,
                color,
                comment
        );
        assertEquals(expectedResult, actual);
    }

    //Проверяет положительный сценарий заказа с кнопки на главной странице
    @Test
    public void checkOrderWorkflowFromMiddle() {
        initWebDriver();

        MainPage mainPage = new MainPage(driver);
        String actual = mainPage.makeOrderFromMiddle(
                name,
                surname,
                address,
                phone,
                metroStationIndex,
                deliveryDay,
                rentalPeriodIndex,
                color,
                comment
        );
        assertEquals(expectedResult, actual);
    }
}
