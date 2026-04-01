package API.Client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetListOrderClient {
    private static final String BASE_URI="https://qa-scooter.praktikum-services.ru/";
    private static final String LIST_ORDER_URI="api/v1/orders";

    @Step
    public ValidatableResponse getListOrder(){
        return  given()
                .when()
                .get(BASE_URI+LIST_ORDER_URI)
                .then().log().all();
    }
}
