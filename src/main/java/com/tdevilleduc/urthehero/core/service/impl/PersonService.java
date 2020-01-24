package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.convertor.PersonConvertor;
import com.tdevilleduc.urthehero.core.dao.PersonDao;
import com.tdevilleduc.urthehero.core.exceptions.PersonNotFoundException;
import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.model.dto.PersonDTO;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonDao personDao;
    @Autowired
    private PersonConvertor personConvertor;

    public boolean exists(final Integer personId) {
        Optional<Person> person = personDao.findById(personId);
        if (person.isEmpty()) {
            log.error("La personne avec l'id {} n'existe pas", personId);
            return false;
        }
        return true;
    }

    public boolean notExists(final Integer personId) {
        return ! exists(personId);
    }

    public Optional<Person> findById(final Integer personId) {
        Assert.notNull(personId, "The personId parameter is mandatory !");
        return personDao.findById(personId);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public PersonDTO createOrUpdate(PersonDTO personDto) {
        Person person = personConvertor.convertToEntity(personDto);
        return personConvertor.convertToDto(personDao.save(person));
    }

    public void delete(Integer personId) {
        Assert.notNull(personId, "The personId parameter is mandatory !");
        Optional<Person> optional = findById(personId);
        optional
            .ifPresentOrElse(person -> personDao.delete(person),
                () -> {
                    throw new PersonNotFoundException(MessageFormatter.format("La personne avec l'id {} n'existe pas", personId).getMessage());
                }
        );
    }
}
