package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    boolean exists(Integer personId);
    boolean notExists(Integer personId);
    Optional<Person> findById(Integer personId);
    List<Person> findAll();
    Person createOrUpdate(Person person);
    void delete(Integer personId);
}
