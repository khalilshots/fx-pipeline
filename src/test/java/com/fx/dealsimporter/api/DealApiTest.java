package com.fx.dealsimporter.api;

import com.fx.dealsimporter.dto.DealRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DealApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void importDeals_happyPath_shouldReturnImportedCount() {
        String body = """
            [
              {
                "dealUniqueId": "API1",
                "fromCurrency": "USD",
                "toCurrency": "EUR",
                "dealTimestamp": "2023-01-01T12:00:00",
                "amount": 150.0
              }
            ]
            """;

        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/deals/import")
        .then()
            .statusCode(200)
            .body("importedCount", equalTo(1))
            .body("failedCount", equalTo(0));
    }
}
