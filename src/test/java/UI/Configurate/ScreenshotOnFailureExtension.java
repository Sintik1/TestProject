package UI.Configurate;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {
    WebDriver driver; // твой драйвер

    public ScreenshotOnFailureExtension(WebDriver driver) {
        this.driver = driver;
        System.out.println("ScreenshotOnFailureExtension инициализируется с драйвером " + driver);
    }

    public ScreenshotOnFailureExtension() {
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()){
            String className = context.getTestClass()
                    .map(Class::getSimpleName)
                    .orElse("unknown_test_class");
            String methodName = context.getTestMethod()
                    .map(m->m.getName())
                    .orElse(context.getDisplayName());
            takeScreenshot(className + "_"+methodName);
        }
        }



    public void setDriver(WebDriver driver){
        this.driver=driver;
    }


    private String sanitizeFileName(String name){
        if (name == null || name.isBlank()){
            return "unknown_test";
        }
        return  name.replaceAll("[\\\\/:*?\"<>|]+","_");
    }
     private void takeScreenshot(String testName) {
        String safeTestName = sanitizeFileName(testName);
        String suffix = "_"+System.currentTimeMillis();
        Path dir = Paths.get("screenshots");
        Path file = dir.resolve(safeTestName + suffix + ".png");

            try {
                Files.createDirectories(dir);
                System.out.println("screenshots dir: " + dir.toAbsolutePath());
                if(driver != null && driver instanceof TakesScreenshot) {
                System.out.println("driver class: " + driver.getClass().getName());
                    byte[]bytes = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                    if(bytes == null || bytes.length == 0){
                    System.out.println("Скриншот не сохранен(пустые байты)");
                    return;
                    }
                    Files.write(file,bytes);
                    System.out.println("Скриншот  сохранен: "+ file.toAbsolutePath());
                    String attachmentName = safeTestName + suffix;
                    Allure.addAttachment(attachmentName,"image/png",new ByteArrayInputStream(bytes),"png");
                    attachScreenshotToAllure(bytes,attachmentName);
                }else{
                    System.out.println("Нельзя сделать скриншот");
            }
        }catch (IOException e) {
                e.printStackTrace();
            }
    }


    /*
    private void takeScreenshot(String testName) {
        String safeTestName = sanitizeFileName(testName);
        String suffix = "_" + System.currentTimeMillis();

        Path errorFile = dir.resolve(safeTestName + suffix + ".error.txt");

        try {



            if (driver != null && driver instanceof TakesScreenshot) {
                System.out.println("driver class: " + driver.getClass().getName());
                byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                if (bytes == null || bytes.length == 0) {
                    Files.writeString(errorFile, "Screenshot bytes are empty", StandardCharsets.UTF_8);
                    System.out.println("Скриншот НЕ сохранен (пустые байты). Ошибка: " + errorFile.toAbsolutePath());
                    return;
                }
                Files.write(file, bytes);
                System.out.println("Скриншот сохранен: " + file.toAbsolutePath());
                attachScreenshotToAllure(bytes, safeTestName + suffix);
            } else {
                String msg = "Нельзя сделать скриншот: driver=" + driver +
                        ", isTakesScreenshot=" + (driver instanceof TakesScreenshot);
                Files.writeString(errorFile, msg, StandardCharsets.UTF_8);
                System.out.println(msg);
                System.out.println("Подробности: " + errorFile.toAbsolutePath());
            }
        } catch (Exception e) {
            try {
                Files.writeString(errorFile, String.valueOf(e), StandardCharsets.UTF_8);
                System.out.println("Ошибка сохранена в: " + errorFile.toAbsolutePath());
            } catch (Exception ignored) {
                // ignore
            }
            e.printStackTrace();
        }
    }

     */
    @Attachment(value = "{name}",type = "image/png")
    private byte[]attachScreenshotToAllure(byte[]bytes , String name)throws IOException{
        return bytes;
    }
}