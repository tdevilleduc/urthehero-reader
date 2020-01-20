package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.exceptions.PersonInternalErrorException;
import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
@RestController
@RequestMapping("/api/person")
class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Callable<ResponseEntity<List<Person>>> getPersons() {
        return () -> {
            return ResponseEntity.ok(personService.findAll());
        };
    }

    @GetMapping(value="/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Callable<ResponseEntity<Person>> getPersonById(@PathVariable Integer personId) {
        return () -> {
            Optional<Person> optional = personService.findById(personId);
            return optional
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };
    }
  
    @PutMapping
    public @ResponseBody
    Callable<ResponseEntity<Person>> createPerson(@RequestBody @NotNull Person person) {
        return () -> {
            if (person.getId() != null && personService.exists(person.getId())) {
                throw new PersonInternalErrorException(MessageFormatter.format("Une personne avec l'identifiant {} existe déjà. Elle ne peut être créée", person.getId()).getMessage());
            }
            return ResponseEntity.ok(personService.createOrUpdate(person));
        };
    }

    @PostMapping
    public @ResponseBody
    Callable<ResponseEntity<Person>> updatePerson(@RequestBody @NotNull Person person) {
        return () -> {
            Assert.notNull(person.getId(), () -> {
                throw new PersonInternalErrorException("L'identifiant de la personne passée en paramètre ne peut pas être null");
            });
            return ResponseEntity.ok(personService.createOrUpdate(person));
        };
    }

    @DeleteMapping(value = "/{personId}")
    public @ResponseBody
    void deletePerson(@PathVariable @NotNull Integer personId) {
        personService.delete(personId);
    }
}
