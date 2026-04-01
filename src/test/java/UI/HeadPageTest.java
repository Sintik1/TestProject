package UI;

import POM.HeadPage;


import UI.Configurate.WebDriverFactory;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.stream.Stream;

import static io.qameta.allure.SeverityLevel.MINOR;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HeadPageTest extends WebDriverFactory  {





    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_ONE.getId(),HeadPage.ListResponse.RESPONSE_ONE.getId(),HeadPage.ListTextResponse.RESPONSE_ONE.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_TWO.getId(),HeadPage.ListResponse.RESPONSE_TWO.getId(),HeadPage.ListTextResponse.RESPONSE_TWO.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_THREE.getId(),HeadPage.ListResponse.RESPONSE_THREE.getId(),HeadPage.ListTextResponse.RESPONSE_THREE.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_FOUR.getId(),HeadPage.ListResponse.RESPONSE_FOUR.getId(),HeadPage.ListTextResponse.RESPONSE_FOUR.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_FIVE.getId(),HeadPage.ListResponse.RESPONSE_FIVE.getId(),HeadPage.ListTextResponse.RESPONSE_FIVE.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_SIX.getId(),HeadPage.ListResponse.RESPONSE_SIX.getId(),HeadPage.ListTextResponse.RESPONSE_SIX.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_SEVEN.getId(),HeadPage.ListResponse.RESPONSE_SEVEN.getId(),HeadPage.ListTextResponse.RESPONSE_SEVEN.getText()),
                Arguments.of(HeadPage.ListQuestions.QUESTIONS_EIGHT.getId(),HeadPage.ListResponse.RESPONSE_EIGHT.getId(),HeadPage.ListTextResponse.RESPONSE_EIGHT.getText())
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    @Epic("Сценарий отображения ответов")
    @Story("Отображение ответов на вопросы")
    @Severity(MINOR)
    public void shouldTextInResponse(String locatorQuestion, String locatorResponse, String expectedResult){
        HeadPage objHeadPage=new HeadPage(driver);
        new HeadPage(driver)
                .clickButtonCookie()
                .scrollToListQuestions()
                .clickToQuestion(By.id(locatorQuestion));
        String actualResult=objHeadPage.getMessageFromResponse(By.id(locatorResponse));
        WebDriverFactory.attachScreenshot(driver,"Ответ на вопрос" + locatorQuestion);
        assertEquals(expectedResult, actualResult, "Текст не соответствует ОР");
    }


}