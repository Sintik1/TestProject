package UI;

import UI.DTO.QuestionsTestData;

import UI.Configurate.BaseTest;

import UI.POM.HeadPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.SeverityLevel.MINOR;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HeadPageTest extends BaseTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new QuestionsTestData(0, HeadPage.ListTextResponse.RESPONSE_ONE.getText())),
                Arguments.of(new QuestionsTestData(1,HeadPage.ListTextResponse.RESPONSE_TWO.getText())),
                Arguments.of(new QuestionsTestData(2,HeadPage.ListTextResponse.RESPONSE_THREE.getText())),
                Arguments.of(new QuestionsTestData(3,HeadPage.ListTextResponse.RESPONSE_FOUR.getText())),
                Arguments.of(new QuestionsTestData(4,HeadPage.ListTextResponse.RESPONSE_FIVE.getText())),
               Arguments.of(new QuestionsTestData(5,HeadPage.ListTextResponse.RESPONSE_SIX.getText())),
                Arguments.of(new QuestionsTestData(6,HeadPage.ListTextResponse.RESPONSE_SEVEN.getText())),
                Arguments.of(new QuestionsTestData(7,HeadPage.ListTextResponse.RESPONSE_EIGHT.getText())
        ));
    }

    @ParameterizedTest
    @MethodSource("testData")
    @Epic("Сценарий отображения ответов")
    @Story("Отображение ответов на вопросы")
    @Severity(MINOR)
    @Attachment
    public void shouldTextInResponse(QuestionsTestData data){
        HeadPage objHeadPage=new HeadPage(driver);

        new HeadPage(driver)
                .clickButtonCookie()
                .scrollToListQuestions()
                        .openFaqQuestion(data.index());
        String actualResult = objHeadPage.readFaqAnswer(data.index());
               // .clickToQuestion(By.id(data.questionsLocatorId()));
       // String actualResult=objHeadPage.getMessageFromResponse(By.id(data.responseLocatorId()));
        assertEquals(data.expectedText(), actualResult, "Текст не соответствует ОР");
        }
    }
