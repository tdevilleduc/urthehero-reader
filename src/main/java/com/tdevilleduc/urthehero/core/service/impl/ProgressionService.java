package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.ProgressionDao;
import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.PersonNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.ProgressionNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.model.Progression;
import com.tdevilleduc.urthehero.core.service.IPageService;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import com.tdevilleduc.urthehero.core.service.IProgressionService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.*;

@Slf4j
@Service
public class ProgressionService implements IProgressionService {

    private IStoryService storyService;
    private IPersonService personService;
    private IPageService pageService;
    private ProgressionDao progressionDao;

    public ProgressionService(IStoryService storyService, IPersonService personService, IPageService pageService, ProgressionDao progressionDao) {
        this.storyService = storyService;
        this.personService = personService;
        this.pageService = pageService;
        this.progressionDao = progressionDao;
    }

    public Progression doProgressionAction(final Integer personId, final Integer storyId, final Integer pageId) {
        Assert.notNull(personId, CHECK_PERSONID_PARAMETER_MANDATORY);
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        Assert.notNull(pageId, CHECK_PAGEID_PARAMETER_MANDATORY);

        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, personId).getMessage());
        }
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId).getMessage());
        }
        if (pageService.notExists(pageId)) {
            throw new PageNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PAGE_DOESNOT_EXIST, pageId).getMessage());
        }

        Optional<Progression> optionalProgression = progressionDao.findByPersonIdAndStoryId(personId, storyId);
        if (optionalProgression.isPresent()) {
            Progression progression = optionalProgression.get();

            // TODO: vérifier qu'on a le droit d'avancer sur cette page depuis la page précédente
            progression.setActualPageId(pageId);

            return progressionDao.save(progression);
        } else {
            throw new ProgressionNotFoundException("Aucune progression avec le personId " + personId + " et le storyId " + storyId);
        }
    }

    public List<Progression> findByPersonId(final Integer personId) {
        Assert.notNull(personId, CHECK_PERSONID_PARAMETER_MANDATORY);
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, personId).getMessage());
        }

        return progressionDao.findByPersonId(personId);
    }

    public Optional<Progression> findByPersonIdAndStoryId(final Integer personId, final Integer storyId) {
        Assert.notNull(personId, CHECK_PERSONID_PARAMETER_MANDATORY);
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, personId).getMessage());
        }
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId).getMessage());
        }

        return progressionDao.findByPersonIdAndStoryId(personId, storyId);
    }

    public Long countByStoryId(final Integer storyId) {
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId).getMessage());
        }

        return progressionDao.countByStoryId(storyId);
    }
}
