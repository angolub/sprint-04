package ru.yandex.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.FAQSection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQSectionTest extends  BaseTest {

    private final int questionIndex;
    private final String expectedAnswer;

    public FAQSectionTest(int questionIndex, String expectedAnswer){
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQ() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете " +
                        "кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    //Проверяет ответы на вопросы
    @Test
    public void checkFAQAnswerTest() {
        initWebDriver();

        FAQSection faqSection = new FAQSection(driver);
        String actualAnswer = faqSection.getAnswerText(questionIndex);
        assertEquals("Ответ на вопрос не совпадает с требуемым", expectedAnswer, actualAnswer);
    }
}
