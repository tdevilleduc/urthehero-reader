package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.model.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    boolean exists(Integer personId);
    boolean notExists(Integer personId);
    Optional<Person> findById(Integer personId);
    List<Person> findAll();
    PersonDTO createOrUpdate(PersonDTO personDto);
    void delete(Integer personId);
}
