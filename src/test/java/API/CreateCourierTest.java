package API;

import API.Client.CourierClient;
import API.POJO.CreateCourierRequest;
import API.POJO.CreateCourierSuccessResponse;
import API.POJO.CreateCourierUnSuccessResponse;
import API.Specification.Specification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static API.DataProvider.CourierData.createCourierData;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.SAME_THREAD)
public class CreateCourierTest {

    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    CourierClient courierClient = new CourierClient();


    @Test
    public void repeatedCourierShouldBeCreated() {
        Specification.installSpecification(Specification.requestSpec(BASE_URI), Specification.responseSpecUniq(SC_CONFLICT));
        CreateCourierRequest createCourierRequest = createCourierData();
        createCourierRequest.setLogin("логин");
        createCourierRequest.setPassword("пароль");
        createCourierRequest.setFirstName("имя");
        courierClient.createCourier(createCourierRequest);
        CreateCourierUnSuccessResponse unSuccessResponse = courierClient.createCourier(createCourierRequest)
                .extract().as(CreateCourierUnSuccessResponse.class);
        assertEquals("Этот логин уже используется. Попробуйте другой.", unSuccessResponse.getMessage());
    }
    @Test
    public void createCourierUnSuccess() {
        Specification.installSpecification(Specification.requestSpec(BASE_URI), Specification.responseSpecUniq(SC_BAD_REQUEST));
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin("");
        CreateCourierUnSuccessResponse createCourierUnSuccessResponse =
                courierClient.createCourier(createCourierRequest)
                        .extract().as(CreateCourierUnSuccessResponse.class);
        assertEquals("Недостаточно данных для создания учетной записи", createCourierUnSuccessResponse.getMessage());
    }

    @Test
    public void createCourierSuccess() {
        Specification.installSpecification(Specification.requestSpec(BASE_URI), Specification.responseSpecUniq(SC_CREATED));
        CreateCourierSuccessResponse createCourierSuccessResponse =
                courierClient.createCourier(createCourierData())
                        .extract().as(CreateCourierSuccessResponse.class);
        assertTrue(createCourierSuccessResponse.getOk());
    }

}
