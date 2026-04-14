package API.Client;

import API.Specification.Specification;
import config.Env;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetListOrderClient {

    private static final String LIST_ORDER_URI="api/v1/orders";

    @Step
    public ValidatableResponse getListOrder(){
        return  given()
                .spec(Specification.requestSpec())
                .when()
                .get(Env.baseUri()+LIST_ORDER_URI)
                .then().log().all();
    }
}
