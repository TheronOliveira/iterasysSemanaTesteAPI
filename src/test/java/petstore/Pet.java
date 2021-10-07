package petstore;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminho)throws java.io.IOException{
        return new String(Files.readAllBytes(Paths.get(caminho)));
    }

    @Test
    public void cadastrarPet() throws java.io.IOException{
        String arquivo = lerJson("src/test/resources/pet1.json");

        RestAssured.given()
                        .log().all()
                    .when()
                        .contentType("application/json")
                        .body(arquivo)
                    .then()
                        .log().all()
                        .statusCode(200)
                    ;

    }
}
