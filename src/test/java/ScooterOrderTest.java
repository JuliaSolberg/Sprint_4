import model.MainPage;
import model.OrderCustomerPage;
import model.RentalPage;
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
public class ScooterOrderTest {
    private static final String BROWSER_NAME = "chrome";
    private static final String HOMEPAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ORDER_CONFIRMATION = "Заказ оформлен";


    // параметры теста
    private final String customerName;
    private final String customerSurname;
    private final String customerAddress;
    private final String customerPhoneNumber;
    private final String comment;

    private WebDriver driver;

    public ScooterOrderTest(String name, String surname, String address, String phone,String comment) {
        this.customerName = name;
        this.customerSurname = surname;
        this.customerAddress = address;
        this.customerPhoneNumber = phone;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Лиза","Дребеденькина","Москва","89867774455","Тра-та-та, тра-та-та, мы везем с собой кота"},
                {"Филлип","Харкушкин","Москва","899900011444","нет"},
        };
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
    public void testScooterSuccessfulOrderDisplayed() {

        // неявное ожидание, которое будет применяться ко всем вызовам методов findElement и findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLowerOrderButton();

        OrderCustomerPage customerPage = new OrderCustomerPage(driver);
        customerPage.fillOutCustomerData(customerName,customerSurname,customerAddress);
        customerPage.fillOutStation();
        customerPage.fillOutPhone(customerPhoneNumber);
        customerPage.clickNextButton();

        RentalPage rentalPage = new RentalPage(driver);
        rentalPage.pickRentalDate();
        rentalPage.chooseRentalPeriod();
        rentalPage.pickScooterColour();
        rentalPage.addComment(comment);
        rentalPage.confirmOrder();

        Assert.assertTrue(rentalPage.orderStatus().contains(ORDER_CONFIRMATION));
    }

    @Test
    public void testScooterSuccessfulOrderDisplayed_upperButton() {

        // неявное ожидание, которое будет применяться ко всем вызовам методов findElement и findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        MainPage mainPage = new MainPage(driver);
        mainPage.clickUpperOrderButton();

        OrderCustomerPage customerPage = new OrderCustomerPage(driver);
        customerPage.fillOutCustomerData(customerName,customerSurname,customerAddress);
        customerPage.fillOutStation();
        customerPage.fillOutPhone(customerPhoneNumber);
        customerPage.clickNextButton();

        RentalPage rentalPage = new RentalPage(driver);
        rentalPage.pickRentalDate();
        rentalPage.chooseRentalPeriod();
        rentalPage.pickScooterColour();
        rentalPage.addComment(comment);
        rentalPage.confirmOrder();

        Assert.assertTrue(rentalPage.orderStatus().contains(ORDER_CONFIRMATION));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
