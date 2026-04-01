package UI.Configurate;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {

    public static WebDriver  createWebDriverChrome(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver= new ChromeDriver(options);
        return driver;
    }

    public static String readProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = WebDriverFactory.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Файл application.properties не найден");
            }
            properties.load(input);
        }
        return properties.getProperty("url");
    }

    @Attachment(value = "{name}",type = "image/png")
    public  static byte[] attachScreenshot(WebDriver driver,String name){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
