package UI.Configurate;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class WebDriverFactory {
    /**
     * Создает ChromeOptions с оптимальными настройками для CI/CD
     */
    protected ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Флаги для CI/контейнеров
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        // Позволяет указать путь до Chrome binary
        String chromeBinary = System.getenv("CHROME_BINARY");
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        return options;
    }

    /**
     * Создает WebDriver: RemoteWebDriver для CI, ChromeDriver для локального запуска
     */
    protected WebDriver createWebDriver(ChromeOptions options) {
        boolean isCi = System.getenv("CI") != null || System.getenv("JENKINS_HOME") != null;
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            try {
                System.out.println("[Test] Using RemoteWebDriver: " + remoteUrl);
                return new RemoteWebDriver(new URL(remoteUrl), options);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid SELENIUM_REMOTE_URL: " + remoteUrl, e);
            }
        }

        if (isCi) {
            throw new IllegalStateException(
                    "CI detected, but SELENIUM_REMOTE_URL is not set. " +
                            "Set SELENIUM_REMOTE_URL (e.g. http://selenium:4444/wd/hub) " +
                            "and BASE_URI (e.g. https://qa-scooter.praktikum-services.ru ) in Jenkins environment."
            );
        }

        System.out.println("[Test] Using local ChromeDriver");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }


   /* public static WebDriver  createWebDriverChrome(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver= new ChromeDriver(options);
        return driver;
    }

    */

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
