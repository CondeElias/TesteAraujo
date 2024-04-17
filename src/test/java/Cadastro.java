import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Cadastro {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void cadastrarNovoPedidoPetComSucesso() {
        RestAssured.baseURI = BASE_URL;

        // JSON payload para o novo pedido
        String requestBody = "{\n" +
                "  \"id\": 198772211,\n" +
                "  \"petId\": 10,\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2024-04-17T08:44:14.456Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        // Realizar a chamada POST para cadastrar o novo pedido
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/store/order");

        // Verificar o código de status e a estrutura da resposta
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("id"), 198772211);
        assertEquals(response.jsonPath().getInt("petId"), 10);
        assertEquals(response.jsonPath().getInt("quantity"), 1);
        assertEquals(response.jsonPath().getString("status"), "placed");

        assertEquals(response.jsonPath().getBoolean("complete"), true);
        System.out.println("Pedido cadastrado com sucesso. ID do pedido: " + response.jsonPath().getInt("id"));
    }
}
