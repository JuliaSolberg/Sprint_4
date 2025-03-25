package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    // LOWER_ORDER_BUTTON - локатор для кнопки "Заказать" внизу страницы
    private static final By LOWER_ORDER_BUTTON = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    // UPPER_ORDER_BUTTON - локатор для кнопки "Заказать" в заголовке страницы
    private static final By UPPER_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Header')]/button[text()='Заказать']");
    // строка для локатора вопроса из раздела "Вопросы о важном"
    private static final String LOCATOR_QUESTION_STRING = "accordion__heading-%d";
    // строка для локатора ответа на вопрос из раздела "Вопросы о важном"
    private static final String LOCATOR_ANSWER_STRING = ".//*[@id = 'accordion__panel-%d']/p";

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLowerOrderButton() {
        WebElement lowerOrderButton = driver.findElement(LOWER_ORDER_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", lowerOrderButton);
        lowerOrderButton.click();
    }

    public void clickUpperOrderButton() {
        WebElement upperOrderButton = driver.findElement(UPPER_ORDER_BUTTON);
        upperOrderButton.click();
    }

    //формируется локатор для вопроса из раздела "Вопросы о важном" по номеру
    public static By getLocatorIDByNumber(int number) {
        return By.id(String.format(LOCATOR_QUESTION_STRING,number));
    }

    //формируется локатор для ответа на вопрос из раздела "Вопросы о важном" по номеру
    public static By getLocatorXpathByNumber(int number) {
        return By.xpath(String.format(LOCATOR_ANSWER_STRING,number));
    }

    public void clickQuestionInFaqSection(int number) {
        WebElement faqFirstListItem = driver.findElement(getLocatorIDByNumber(number));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqFirstListItem);
        faqFirstListItem.click();
    }

    public String getAnswerInFaqSection(int number) {
        WebElement faqAnswer = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(getLocatorXpathByNumber(number)));
        return faqAnswer.getText();
    }
}
