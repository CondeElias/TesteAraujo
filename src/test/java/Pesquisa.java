import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Pesquisa {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void pesquisarPetInexistente() {
        RestAssured.baseURI = BASE_URL;

        // Id de um pet que não existe na API
        int petId = 999999999;

        // Realizar a chamada GET para buscar o pet inexistente
        Response response = given()
                .get("/pet/" + petId);

        // Verificar o código de status e a mensagem de erro
        assertEquals(response.getStatusCode(), 404);
        assertEquals(response.jsonPath().getString("message"), "Pet not found");
        System.out.println("Pesquisando pelo pet com ID: " + petId);
        System.out.println("Pet não encontrado. Mensagem da API: " + response.jsonPath().getString("message"));
        System.out.println("Detalhes da resposta da API: " + response.getBody().asString());
    }
}
