package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PageControllerTest extends AbstractTest {

    private static final String uriController = "/api/page";
    @Test
    public void testGetPageById() {
        given()
                .when().get(uriController + "/1")
                .then()
                .statusCode(200);;
    }
}
