package API.Client;

import API.POJO.CreateCourierRequest;
import API.Specification.Specification;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class CourierClient {
    public static final String BASE_URI="https://qa-scooter.praktikum-services.ru/";
    public static final String CREATE_COURIER= "api/v1/courier";

    public ValidatableResponse createCourier(CreateCourierRequest createCourierRequest){
        Specification.requestSpec(BASE_URI);
        return given()
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER)
                .then().log().all();

    }
}
