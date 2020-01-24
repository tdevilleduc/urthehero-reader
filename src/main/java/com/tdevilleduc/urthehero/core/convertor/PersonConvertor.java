package com.tdevilleduc.urthehero.core.convertor;

import com.tdevilleduc.urthehero.core.model.Person;
import com.tdevilleduc.urthehero.core.model.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConvertor {

    @Autowired
    ModelMapper modelMapper;

    public PersonDTO convertToDto(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertToEntity(PersonDTO personDto) {
        return modelMapper.map(personDto, Person.class);
    }
}
