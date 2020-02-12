package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.CharacterDao;
import com.tdevilleduc.urthehero.core.exceptions.CharacterNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.PersonNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.YouAreDeadException;
import com.tdevilleduc.urthehero.core.model.Character;
import com.tdevilleduc.urthehero.core.service.ICharacterService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.*;

@Slf4j
@Service
public class CharacterService implements ICharacterService {

    private PersonService personService;
    private StoryService storyService;
    private CharacterDao characterDao;

    public CharacterService(PersonService personService, StoryService storyService, CharacterDao characterDao) {
        this.personService = personService;
        this.storyService = storyService;
        this.characterDao = characterDao;
    }

    public List<Integer> findStoryByPersonId(final Integer personId) {
        Assert.notNull(personId, CHECK_PERSONID_PARAMETER_MANDATORY);
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, personId).getMessage());
        }

        return characterDao.findByPersonId(personId).stream().map(Character::getStoryId).collect(Collectors.toList());
    }

    public Character updateLife(Integer personId, Integer storyId, Integer changeLife) {
        Character character = characterDao.findByPersonIdAndStoryId(personId, storyId);
        Integer currentLifePoints = character.getLifePoints();

        log.info(MessageFormatter.format(INFO_MESSAGE_CHARACTER_LIFE, currentLifePoints).getMessage());

        if (currentLifePoints - changeLife <= 0) {
            throw new YouAreDeadException();
        } else {
            character.setLifePoints(currentLifePoints - changeLife);
            character = characterDao.save(character);
        }

        return character;
    }

    public Character changePage(Integer personId, Integer storyId, Integer newPageId) {
        Character character = characterDao.findByPersonIdAndStoryId(personId, storyId);
        if (character == null) {
            throw new CharacterNotFoundException(MessageFormatter.format(ERROR_MESSAGE_CHARACTER_DOESNOT_EXIST, personId, storyId).getMessage());
        }

        log.info(MessageFormatter.format(INFO_MESSAGE_CHARACTER_CURRENT_PAGE, newPageId).getMessage());

        character.setPageId(newPageId);
        character = characterDao.save(character);

        return character;
    }

    public Integer findCurrentPageByPersonIdAndStoryId(final Integer personId, final Integer storyId) {
        Assert.notNull(personId, CHECK_PERSONID_PARAMETER_MANDATORY);
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, personId).getMessage());
        }
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId).getMessage());
        }
        Character character = characterDao.findByPersonIdAndStoryId(personId, storyId);
        if (character == null) {
            throw new CharacterNotFoundException(MessageFormatter.format(ERROR_MESSAGE_CHARACTER_DOESNOT_EXIST, personId, storyId).getMessage());
        }
        
        return character.getPageId();
    }


}
