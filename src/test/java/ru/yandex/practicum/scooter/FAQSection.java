package ru.yandex.practicum.scooter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQSection extends BasePage{
    //секция с вопросами
    @FindBy(className = "Home_FourPart__1uthg")
    private WebElement faqSection;
    //кнопка вопроса
    private final String questionXpathFormat = ".//div[@id='accordion__heading-%d']/parent::div";
    //ответ
    private final String answerIdFormat = "accordion__panel-%d";

    public FAQSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        CookieSection cookieSection = new CookieSection(driver);
        cookieSection.acceptCookie();
    }

    private void scrollToSection(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqSection);
    }

    //По индексу вопроса нажимает кнопку и возвращает ответ 
    public String getAnswerText(int questionIndex) {
        //скроллим до секции вопросов
        scrollToSection();
        //нажимает кнопку вопроса
        driver.findElement(
                By.xpath(String.format(questionXpathFormat, questionIndex))
        ).click();
        //ожидает появдения ответа
        By answerLocator = By.id(String.format(answerIdFormat, questionIndex));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        //возвращает текст ответа
        return driver.findElement(answerLocator).getText();
    }
}
