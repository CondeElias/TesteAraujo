import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Atualizar {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void atualizarDadosPetExistente() {
        RestAssured.baseURI = BASE_URL;

        // JSON payload para atualizar os dados do pet
        String requestBody = "{\n" +
                "  \"id\": 10,\n" +
                "  \"category\": {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"dog\"\n" +
                "  },\n" +
                "  \"name\": \"Rex\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://www.example.com/rex.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"tag1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        // Realizar a chamada PUT para atualizar o pet
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/pet");

        // Verificar o código de status e a estrutura da resposta
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("id"), 10);
        assertEquals(response.jsonPath().getString("name"), "Rex");
        assertEquals(response.jsonPath().getString("status"), "available");
        System.out.println("Pet atualizado com sucesso. ID do pet: " + response.jsonPath().getInt("id"));
        System.out.println("Nome do pet atualizado com sucesso. Novo nome: " + response.jsonPath().getString("name"));
        System.out.println("Status do pet atualizado com sucesso. Novo status: " + response.jsonPath().getString("status"));
        System.out.println("Atualização do pet concluída com sucesso.");
    }
}
