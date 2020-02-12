package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@Tag("integration")
public class PlayControllerTest extends AbstractTest {

    private static final String uriController = "/api/play";

    @Test
    public void beginStory_thenSuccess() {
        given()
                .when().get(uriController + "/person/1/story/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void resumeStory_thenSuccess() {
        given()
                .when().get(uriController + "/resume/person/1/story/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void postProgressionAction_thenSuccess() {
        given()
                .when().get(uriController + "/person/1/story/1/page/2")
                .then()
                .statusCode(200);
    }
}
