package API.Client;

import API.POJO.CreateCourierRequest;
import API.Specification.Specification;
import config.Env;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class CourierClient {

    public static final String CREATE_COURIER= "api/v1/courier";

    public ValidatableResponse createCourier(CreateCourierRequest createCourierRequest){
        return given()
                .spec(Specification.requestSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER)
                .then().log().all();

    }
}
