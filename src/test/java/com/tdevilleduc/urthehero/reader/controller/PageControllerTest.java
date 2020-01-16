package com.tdevilleduc.urthehero.reader.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PageControllerTest  {

    private static final String uriController = "/api/page";
    @Test
    public void testGetPageById() {
        given()
                .when().get(uriController + "/1")
                .then()
                .statusCode(200);
//                .body(is("hello"));
    }
}
