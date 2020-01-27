package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.model.dto.PersonDTO;
import com.tdevilleduc.urthehero.core.service.impl.PersonService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@QuarkusTest
public class PersonServiceTest extends AbstractTest {

    @Autowired
    PersonService personService;

    private static final Integer personId_exists = 1;
    private static final Integer personId_notExists = 123456789;

    @Test
    public void exists_thenFound() {
        boolean exists = personService.exists(personId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = personService.exists(personId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenFound() {
        boolean notExists = personService.notExists(personId_exists);
        Assertions.assertFalse(notExists);
    }

    @Test
    public void notExists_thenNotFound() {
        boolean notExists = personService.notExists(personId_notExists);
        Assertions.assertTrue(notExists);
    }

    @Test
    public void findAll_thenSuccess() {
        List<Person> listPerson = personService.findAll();
        Assertions.assertNotNull(listPerson);
        Assertions.assertEquals(3, listPerson.size());
    }

    @Test
    public void findById_thenSuccess() {
        Optional<Person> optionalPerson = personService.findById(personId_exists);
        Assertions.assertNotNull(optionalPerson);
        Assertions.assertTrue(optionalPerson.isPresent());
        Person person = optionalPerson.get();
        Assertions.assertNotNull(person);
        Assertions.assertEquals("Thomas Deville-Duc", person.getDisplayName());
        Assertions.assertEquals("thomas@gmail.com", person.getEmail());
        Assertions.assertEquals("tdevilleduc", person.getLogin());
        Assertions.assertEquals("password", person.getPassword());
    }

    @Test
    public void create_thenSuccess() {

        String login = new RandomDataGenerator().nextHexString(20);
        String displayName = new RandomDataGenerator().nextHexString(20);
        String email = new RandomDataGenerator().nextHexString(20);
        String password = new RandomDataGenerator().nextHexString(20);

        PersonDTO originPersonDTO = new PersonDTO(login, displayName, email, password);
        PersonDTO personDto = personService.createOrUpdate(originPersonDTO);
        Assertions.assertNotNull(personDto);
        Assertions.assertEquals(originPersonDTO.toString(), personDto.toString());
    }
}
