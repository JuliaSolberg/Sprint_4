package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentalPage {

    //поле выбора даты аренды
    private static final By INPUT_DATE_FIELD = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[contains(@placeholder, 'Когда')]");
    //дата аренды выбранная из выпадающего календаря
    private static final By PICK_THE_DATE = By.xpath(".//div[contains(@class, 'react-datepicker__month-container')]/div[2]/div[5]/div[3]");
    //поле выбора срока аренды
    private static final By RENTAL_PERIOD_FIELD = By.xpath(".//div[contains(@class, 'Dropdown-root')]");
    //срок аренды, выбранный их списка
    private static final By PICK_THE_RENTAL_PERIOD = By.xpath(".//div[contains(@class, 'Dropdown-root')]/div[2]/div[1]");
    //чекбок выбора цвета скутера
    private static final By PICK_SCOOTER_COLOUR = By.xpath(".//div[contains(@class, 'Order_Checkboxes__3lWSI')]/label[1]");
    //поле ввода комментрария
    private static final By INPUT_COMMENT_FIELD = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Комментарий')]");
    //кнопка Заказать
    private static final By ORDER_BUTTON_LAST = By.xpath(".//div[contains(@class, 'Order_Buttons__1xGrp')]/button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Заказать')]");
    //кнопка Да
    private static final By YES_BUTTON = By.xpath(".//div[contains(@class, 'Order_Buttons__1xGrp')]/button[contains(@class, 'Button_Button__ra12g') and text()='Да']");
    //форма информации о заказе
    private static final By ORDER_STATUS = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'оформлен')]");

    private WebDriver driver;

    public RentalPage(WebDriver driver) {
        this.driver = driver;
    }

    public void pickRentalDate () {
        WebElement inputDateField = new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(INPUT_DATE_FIELD));
        inputDateField.click();
        WebElement dateOption = driver.findElement(PICK_THE_DATE);
        dateOption.click();
    }

    public void chooseRentalPeriod() {
        WebElement rentalPeriod = driver.findElement(RENTAL_PERIOD_FIELD);
        rentalPeriod.click();
        WebElement pickRentalPeriod = driver.findElement(PICK_THE_RENTAL_PERIOD);
        pickRentalPeriod.click();
    }

    public void pickScooterColour() {
        WebElement pickColour = driver.findElement(PICK_SCOOTER_COLOUR);
        pickColour.click();
    }

    public void addComment(String comment) {
        WebElement commentField = driver.findElement(INPUT_COMMENT_FIELD);
        commentField.sendKeys(comment);
    }

    public void confirmOrder() {
        WebElement confirmOrder = driver.findElement(ORDER_BUTTON_LAST);
        confirmOrder.click();
        WebElement yesButton = new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(YES_BUTTON));
        yesButton.click();
    }

    public String orderStatus() {
        WebElement orderStatus = new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(ORDER_STATUS));
        return orderStatus.getText();
    }
}
