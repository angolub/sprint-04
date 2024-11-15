package ru.yandex.practicum;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.practicum.scooter.MainPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String scooterURL = "https://qa-scooter.praktikum-services.ru/";

    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final int metroStationIndex;
    private final  int deliveryDay;
    private final int rentalPeriodIndex;
    private final String color;
    private final String comment;
    //private final String expectedResult = "Хотите оформить заказ?";
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
                        "Длинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинныйдлинный",
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

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void checkOrderWorkflowFromHeader() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

    @Test
    public void checkOrderWorkflowFromMiddle() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

    @After
    public void teardown() {
        driver.quit();
    }
}
