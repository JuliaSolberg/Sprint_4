import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ScooterWebAppTest {
    private static final String HOMEPAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String EXPECTED_ANSWER_TEXT_TWO = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String EXPECTED_ANSWER_TEXT_FOUR = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";

    // параметры теста
    private final int questionNumber;
    private final int answerNumber;
    private final String expectedAnswer;

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {1, 1, EXPECTED_ANSWER_TEXT_TWO},
                {3, 3, EXPECTED_ANSWER_TEXT_FOUR},
        };
    }

    private WebDriver driver;

    public ScooterWebAppTest(int numberQ, int numberA, String text) {
        this.questionNumber = numberQ;
        this.answerNumber = numberA;
        this.expectedAnswer = text;
    }

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        driver.get(HOMEPAGE_URL);
    }

    @Test
    public void testFaqAnswerDisplayed() {

        // неявное ожидание, которое будет применяться ко всем вызовам методов findElement и findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

       /* MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestionInFaqSection();
        Assert.assertEquals("Ответ не корректный", EXPECTED_ANSWER_TEXT, mainPage.getAnswerInFaqSection());*/

        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestionInFaqSection(questionNumber);
        String answer = mainPage.getAnswerInFaqSection(answerNumber);
        Assert.assertEquals("Ответ не корректный", expectedAnswer, answer);
    }

    // после теста независимо от его результата закрываем браузер
    @After
    public void tearDown() {
        driver.quit();
    }
}
