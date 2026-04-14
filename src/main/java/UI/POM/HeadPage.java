package UI.POM;

import UI.util.Waits;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HeadPage {

    private final WebDriver driver;
    private final Waits waits;


    //Локатор кнопки "Заказать" в хедере страницы
    private final By buttonOrderInHeaderPage =  By.xpath("//button[normalize-space(.)='Статус заказа']/preceding-sibling::button[normalize-space(.)='Заказать']");;

    //Локатор кнопки "Статус заказа" в хедере страницы
    private final By buttonStatusOrderInHeaderPage = By.xpath("//button[normalize-space(.)='Статус заказа']");

    //Локатор кнопки принятия Cookie
    private final By buttonCookieInHeadPage = By.id("rcc-confirm-button");

    //Локатор кнопки "Заказать" в середине страницы
    private final By buttonOrderInCentreHeadPage = By.xpath("//button[contains(@class,'Button_Middle')and normalize-space(.)='Заказать']");

    //Локатор поля для ввода номера заказа
    private final By fieldSendNumberOrder = By.cssSelector("input[placeholder = 'Введите номер заказа']");

    //Локатор кнопки Go
    private final By buttonGo = By.xpath("//button[contains(@class,'Header_Button')and normalize-space(.)='Go!']");

    //Локатор для отображения главной страницы
    private final By headPage = By.cssSelector("div.Home_BluePrint__TGX2n img[alt ='Scooter blueprint']");

    public HeadPage(WebDriver driver) {
        this.driver = driver;
        this.waits=new Waits(driver);

    }

    public enum ListQuestions {
        QUESTIONS_ONE("accordion__heading-0"),
        QUESTIONS_TWO("accordion__heading-1"),
        QUESTIONS_THREE("accordion__heading-2"),
        QUESTIONS_FOUR("accordion__heading-3"),
        QUESTIONS_FIVE("accordion__heading-4"),
        QUESTIONS_SIX("accordion__heading-5"),
        QUESTIONS_SEVEN("accordion__heading-6"),
        QUESTIONS_EIGHT("accordion__heading-7");

        private final String id;

        ListQuestions(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public enum ListResponse {
        RESPONSE_ONE("accordion__panel-0"),
        RESPONSE_TWO("accordion__panel-1"),
        RESPONSE_THREE("accordion__panel-2"),
        RESPONSE_FOUR("accordion__panel-3"),
        RESPONSE_FIVE("accordion__panel-4"),
        RESPONSE_SIX("accordion__panel-5"),
        RESPONSE_SEVEN("accordion__panel-6"),
        RESPONSE_EIGHT("accordion__panel-7");

        private final String id;

        ListResponse(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public enum ListTextResponse {
        RESPONSE_ONE("Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
        RESPONSE_TWO("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
        RESPONSE_THREE("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
        RESPONSE_FOUR("Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
        RESPONSE_FIVE("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
        RESPONSE_SIX("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
        RESPONSE_SEVEN("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
        RESPONSE_EIGHT("Да, обязательно. Всем самокатов! И Москве, и Московской области.");

        private final String text;

        ListTextResponse(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }





    @Description("Вспомогательный метод клика по кнопке")
    public void clickButton(By locator) {
        waits.waitOfPrecense(locator);
        waits.waitForClickable(locator);
        driver.findElement(locator).click();
    }

    @Step("Метод клика по кнопке куки")
    public HeadPage clickButtonCookie() {
        clickButton(buttonCookieInHeadPage);
        return this;
    }

    @Step("Метод скрола до списка вопросов")
    public HeadPage scrollToListQuestions() {
        WebElement element = waits.waitOfPrecense(By.id(ListQuestions.QUESTIONS_EIGHT.getId()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        return this;
    }

    @Step("Метод клика по вопросу")
    public HeadPage openFaqQuestion(int index){
        waits.waitForClickable(By.id("accordion__heading-"+index));
         driver.findElement(By.id("accordion__heading-"+index))
        .click();
        return this;
    }
   /* public HeadPage clickToQuestion(By locator) {
        waitForVisible(locator);
        clickButton(locator);
        return this;
    }

    */

    @Step("Метод получения текста из ответа")
    /*public String getMessageFromResponse(By locator) {
        WebElement element = waitForVisible(locator);
        return element.getText();
    }
     */
    public String readFaqAnswer(int index){
        waits.waitForVisible(By.id("accordion__panel-"+index));
        WebElement element = driver.findElement(By.id("accordion__panel-"+index));
        return element.getText();


    }
    @Step("Метод получения клика по кнопке Заказать в середине странице")
    public HeadPage clickButtonOrderInCenterPage() {
        waits.waitForVisible(buttonOrderInCentreHeadPage);
        waits.waitForClickable(buttonOrderInCentreHeadPage);
        clickButton(buttonOrderInCentreHeadPage);
        return this;
    }

    @Step("Метод клика по кнопке GO")
    public HeadPage clickButtonGo() {
        waits.waitForClickable(buttonGo);
        clickButton(buttonGo);
        return this;
    }

    @Step("Метод ввода номера ID заказа в поле для статуса заказа")
    public HeadPage sendNumberId(Integer id) {
       waits.waitForClickable(fieldSendNumberOrder);
        driver.findElement(fieldSendNumberOrder).sendKeys(id.toString());
        return this;
    }

    @Step("Метод проверки отображения видимости главной страницы")
    public boolean isVisibleHeadPage() {
        waits.waitForVisible(headPage);
        return true;
    }
}
