package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderCustomerPage {
    //поле ввода фамилии
    private static final By INPUT_SURNAME_FIELD = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and @placeholder='* Фамилия']");
    //поле ввода имени
    private static final By INPUT_NAME_FIELD = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and @placeholder='* Имя']");
    //поле ввода адреса
    private static final By INPUT_ADDRESS_FIELD = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Адрес')]");
    //поле выбора станции метро
    private static final By INPUT_METRO_FIELD = By.xpath(".//input[contains(@class, 'select-search__input')]");
    //станция метро из списка
    private static final By STATION_DROPDOWN_LIST = By.xpath(".//div[contains(@class, 'select-search')]/div[2]/ul/li[1]");
    //поле ввода телефона
    private static final By INPUT_PHONE_FIELD = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Телефон')]");
    //кнопка Далее
    private static final By NEXT_BUTTON = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button");

    private WebDriver driver;

    public OrderCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOutCustomerData(String name, String surname, String address) {
        WebElement inputNameField = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(INPUT_NAME_FIELD));
        inputNameField.sendKeys(name);
        driver.findElement(INPUT_SURNAME_FIELD).sendKeys(surname);
        driver.findElement(INPUT_ADDRESS_FIELD).sendKeys(address);
    }

    public void fillOutStation() {
        WebElement dropDownStation = driver.findElement(INPUT_METRO_FIELD);
        dropDownStation.click();
        WebElement stationOption = driver.findElement(STATION_DROPDOWN_LIST);
        stationOption.click();
    }

    public void fillOutPhone(String phone) {
        driver.findElement(INPUT_PHONE_FIELD).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }
}
