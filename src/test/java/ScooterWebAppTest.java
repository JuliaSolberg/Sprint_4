import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ScooterWebAppTest {
    private static final String BROWSER_NAME = "chrome";
    private static final String HOMEPAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String EXPECTED_ANSWER_TEXT_ONE = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String EXPECTED_ANSWER_TEXT_TWO = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String EXPECTED_ANSWER_TEXT_THREE = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String EXPECTED_ANSWER_TEXT_FOUR = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String EXPECTED_ANSWER_TEXT_FIVE = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String EXPECTED_ANSWER_TEXT_SIX = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String EXPECTED_ANSWER_TEXT_SEVEN = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String EXPECTED_ANSWER_TEXT_EIGHT = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    // параметры теста
    private final int questionNumber;
    private final int answerNumber;
    private final String expectedAnswer;

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, 0, EXPECTED_ANSWER_TEXT_ONE},
                {1, 1, EXPECTED_ANSWER_TEXT_TWO},
                {2, 2, EXPECTED_ANSWER_TEXT_THREE},
                {3, 3, EXPECTED_ANSWER_TEXT_FOUR},
                {4, 4, EXPECTED_ANSWER_TEXT_FIVE},
                {5, 5, EXPECTED_ANSWER_TEXT_SIX},
                {6, 6, EXPECTED_ANSWER_TEXT_SEVEN},
                {7, 7, EXPECTED_ANSWER_TEXT_EIGHT},
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
        driver = getWebDriver(BROWSER_NAME);
        driver.get(HOMEPAGE_URL);
    }

    public static WebDriver getWebDriver(String browserName) {
        if (browserName.equals("firefox")) {
            return new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            return new ChromeDriver();
        } else {
            throw new RuntimeException("Неизвестный браузер: " + browserName);
        }
    }

    @Test
    public void testFaqAnswerDisplayed() {

        // неявное ожидание, которое будет применяться ко всем вызовам методов findElement и findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

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
