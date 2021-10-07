package petstore;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminho)throws java.io.IOException{
        return new String(Files.readAllBytes(Paths.get(caminho)));
    }

    @Test
    public void cadastrarPet() throws java.io.IOException{
        String arquivo = lerJson("src/test/resources/pet1.json");

        given()
                .log().all()
                .contentType("application/json")
        .when()
                .body(arquivo)
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("dog"))
                .body("tags.name", contains("semanatestapi"))
                .body("status", containsString("available") )
        ;
    }
    @Test
    public void buscarPetId(){
        String petId = "9223372036854775807";

        given()
                .log().all()
                .contentType("application/json")
        .when()
                .get(uri +"/"+ petId)
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void atualizarPet() throws IOException {
        String arquivo = lerJson("src/test/resources/pet2.json");

        given()
                .log().all()
                .contentType("application/json")
                .body(arquivo)
        .when()
                .put(uri)
        .then()
                .statusCode(200)
                .body("status", is("sold"))
        ;
    }

    public void deletarPet(){
        String petId = "9223372036854775807";

        given()
                .log().all()
                .contentType("application/json")
        .when()
                .delete(uri + "/" + petId)
        .then()
                .statusCode(200)
                .body("code", is("200"))
        ;
    }
}
