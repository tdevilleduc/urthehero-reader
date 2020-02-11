package com.tdevilleduc.urthehero.core.convertor;

import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.model.dto.PersonDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConvertor {

    public PersonDTO convertToDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setLogin(person.getLogin());
        personDTO.setPassword(person.getPassword());
        personDTO.setDisplayName(person.getDisplayName());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }

    public Person convertToEntity(PersonDTO personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setDisplayName(personDto.getDisplayName());
        person.setEmail(personDto.getEmail());
        return person;
    }
}
