package com.tdevilleduc.urthehero.reader;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractTest {

    @ClassRule
    public static final MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.0.19")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    protected static void setupMysql() {
        mySqlContainer.start();
        System.setProperty("quarkus.datasource.url", mySqlContainer.getJdbcUrl());
        System.setProperty("quarkus.datasource.username", "test");
        System.setProperty("quarkus.datasource.password", "test");
    }
}
