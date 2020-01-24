package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class StoryControllerTest extends AbstractTest {

    private static final String uriController = "/api/story";

    @Test
    public void getStoryById_thenSuccess() {
        given()
                .when().get(uriController + "/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllStories_thenSuccess() {
        given()
                .when().get(uriController + "/all")
                .then()
                .statusCode(200);
    }
}
