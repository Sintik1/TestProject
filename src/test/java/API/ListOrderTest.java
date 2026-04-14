package API;

import API.POJO.GetListOrderResponse;
import API.Specification.Specification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.SAME_THREAD)
public class ListOrderTest {
    @Test
    public void getListOrder(){
        List<GetListOrderResponse> listOrder =
                given()
                        .spec(Specification.requestSpec())
                        .when()
                        .get("/api/v1/orders")
                        .then().log().all()
                        .statusCode(SC_OK)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("orders",GetListOrderResponse.class);
        listOrder.stream().forEach(x->assertNotNull(x.getId()));
        List<Integer>trackNUmber = listOrder.stream().map(GetListOrderResponse::getTrack).collect(Collectors.toList());
       assertNotNull(trackNUmber,"Track не должен быть пустым");

    }

    @Test
    public void getIdFromName(){
        List<GetListOrderResponse>idFromNameNaruto=
                given()
                        .spec(Specification.requestSpec())
                        .when()
                        .get("/api/v1/orders")
                        .then().log().all()
                        .statusCode(SC_OK)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("orders",GetListOrderResponse.class);
        idFromNameNaruto.stream().filter(x->"Naruto".equals(x.getFirstName())).map(GetListOrderResponse::getId).collect(Collectors.toList());
        assertFalse(idFromNameNaruto.isEmpty(),"заказов с наруто не найдено");


    }
    @Test
    public void getIdFromLastName(){
        List<GetListOrderResponse>idFromLastNameUchiha=
                given()
                        .spec(Specification.requestSpec())
                        .when()
                        .get("/api/v1/orders")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .body().jsonPath()
                        .getList("orders", GetListOrderResponse.class);
        idFromLastNameUchiha.stream().filter(x->"Uchiha".equals(x.getLastName())).map(GetListOrderResponse::getId).collect(Collectors.toList());
        assertFalse(idFromLastNameUchiha.isEmpty(),"Заказов не надейно");
    }


    @Test
    public void getphone() {
        List<GetListOrderResponse> listOrderPhone =
                given()
                        .spec(Specification.requestSpec())
                        .get("/api/v1/orders")
                        .then().log().all()
                        .statusCode(SC_OK)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("orders", GetListOrderResponse.class);

        // Получаем список ID заказов с непустым телефоном и сортируем
        List<Integer> idWithPhone = listOrderPhone.stream()
                .filter(x -> x.getPhone() != null && !x.getPhone().isEmpty())
                .sorted(Comparator.comparing(GetListOrderResponse::getId))
                .map(GetListOrderResponse::getId)
                .collect(Collectors.toList());

        // Проверка, есть ли заказы с телефоном
        assertFalse(idWithPhone.isEmpty(),"Нет заказов с непустым phone");

        // Выводим
        idWithPhone.forEach(id -> System.out.println("Заказ с непустым телефоном: " + id));
        System.out.println(idWithPhone.size());
    }

    @Test
    public void orderIsEmptyName(){
        List<GetListOrderResponse>listOrder=
                given()
                        .spec(Specification.requestSpec())
                        .get("/api/v1/orders")
                        .then().log().all()
                        .statusCode(SC_OK)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("order",GetListOrderResponse.class);
        List<Integer>idWithName = listOrder.stream().filter(x->x.getFirstName()!=null &&!x.getFirstName().isEmpty()).sorted(Comparator.comparing(GetListOrderResponse::getFirstName)).map(GetListOrderResponse::getId).collect(Collectors.toList());

        assertTrue(idWithName.isEmpty(),"нет заказов с пустым name");
        idWithName.forEach(id->System.out.println("Заказ с непустым name "+id));
    }
}
