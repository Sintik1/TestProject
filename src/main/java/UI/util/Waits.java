package UI.util;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
private final WebDriver driver;
private static  WebDriverWait wait;

    public Waits(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver,Timeouts.UI_TIMEOUTS);

    }

    @Description("Вспомогательный метод ожидания кликабельности элемента")
    public static WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    @Description("Вспомогательный метод ожидания видимости элемента")
    public static WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
