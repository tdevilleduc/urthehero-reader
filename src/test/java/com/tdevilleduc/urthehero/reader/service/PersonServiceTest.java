package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.service.impl.PersonService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
public class PersonServiceTest extends AbstractTest {

    @Autowired
    PersonService personService;

    private static final Integer personId_exists = 1;
    private static final Integer personId_notExists = 123456789;

    @Test
    public void exists_thenSuccess() {
        boolean exists = personService.exists(personId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = personService.exists(personId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenSuccess() {
        boolean notExists = personService.notExists(personId_notExists);
        Assertions.assertTrue(notExists);
    }
}
