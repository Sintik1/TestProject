package API;

import API.Client.CourierClient;
import API.POJO.CreateCourierRequest;
import API.POJO.CreateCourierSuccessResponse;
import API.POJO.CreateCourierUnSuccessResponse;
import API.Specification.Specification;
import config.Env;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static API.DataProvider.CourierData.createCourierData;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;


@Execution(ExecutionMode.SAME_THREAD)
public class CreateCourierTest {


    CourierClient courierClient = new CourierClient();


    @Test
    public void repeatedCourierShouldBeCreated() {
        CreateCourierRequest createCourierRequest = createCourierData();
        createCourierRequest.setLogin("логин");
        createCourierRequest.setPassword("пароль");
        createCourierRequest.setFirstName("имя");
        courierClient.createCourier(createCourierRequest);
        CreateCourierUnSuccessResponse unSuccessResponse = courierClient.createCourier(createCourierRequest)
                .statusCode(SC_CONFLICT)
                .extract().as(CreateCourierUnSuccessResponse.class);
        assertEquals("Этот логин уже используется. Попробуйте другой.", unSuccessResponse.getMessage());
    }
    @Test
    public void createCourierUnSuccess() {

        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin("");
        CreateCourierUnSuccessResponse createCourierUnSuccessResponse =
                courierClient.createCourier(createCourierRequest)
                        .statusCode(SC_BAD_REQUEST)
                        .extract().as(CreateCourierUnSuccessResponse.class);
        assertEquals("Недостаточно данных для создания учетной записи", createCourierUnSuccessResponse.getMessage());
    }

    @Test
    public void createCourierSuccess() {
        CreateCourierSuccessResponse createCourierSuccessResponse =
                courierClient.createCourier(createCourierData())
                        .statusCode(SC_CREATED)
                        .extract().as(CreateCourierSuccessResponse.class);
        assertTrue(createCourierSuccessResponse.getOk());
    }

}
