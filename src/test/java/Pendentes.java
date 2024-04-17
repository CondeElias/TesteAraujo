import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Pendentes {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void pesquisarPetsComStatusPending() {
        RestAssured.baseURI = BASE_URL;

        // Realizar a chamada GET para buscar pets com status "pending"
        Response response = given()
                .queryParam("status", "pending")
                .get("/pet/findByStatus");

        // Verificar o código de status e a quantidade de pets retornados
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getList("status").size(), 2); // Altere de acordo com a quantidade esperada de pets com status "pending"
        System.out.println("Quantidade de pets com status 'pending': " + response.jsonPath().getList("status").size());
        List<Integer> pendingPetIds = response.jsonPath().getList("id");
        System.out.println("IDs dos pets com status 'pending': " + pendingPetIds);
        List<Map<String, Object>> pendingPets = response.jsonPath().getList(".");
        for (Map<String, Object> pet : pendingPets) {
            System.out.println("ID: " + pet.get("id"));
            System.out.println("Nome: " + pet.get("name"));
            System.out.println("Status: " + pet.get("status"));
            System.out.println("--------------------------");
        }

    }
}
