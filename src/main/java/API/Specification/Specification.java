package API.Specification;

import config.Env;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.qameta.allure.*;


import static org.apache.http.HttpStatus.*;

public class Specification {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Env.baseUri())
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification responseSpecOk() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .build();
    }
    public static ResponseSpecification responseSpecCreated() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_CREATED)
                .build();
    }
    public static ResponseSpecification responseSpecError() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification responseSpecUniq(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
