package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.ProgressionDao;
import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.PersonNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.ProgressionNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.Progression;
import com.tdevilleduc.urthehero.core.service.IPageService;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import com.tdevilleduc.urthehero.core.service.IProgressionService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProgressionService implements IProgressionService {

    @Autowired
    private IStoryService storyService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IPageService pageService;
    @Autowired
    private ProgressionDao progressionDao;

    public Progression doProgressionAction(final Integer personId, final Integer storyId, final Integer pageId) {
        Assert.notNull(personId, "The personId parameter is mandatory !");
        Assert.notNull(storyId, "The storyId parameter is mandatory !");
        Assert.notNull(pageId, "The pageId parameter is mandatory !");

        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format("La personne avec l'id {} n'existe pas", personId).getMessage());
        }
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format("L'histoire avec l'id {} n'existe pas", storyId).getMessage());
        }
        if (pageService.notExists(pageId)) {
            throw new PageNotFoundException(MessageFormatter.format("La page avec l'id {} n'existe pas", pageId).getMessage());
        }

        Optional<Progression> optionalProgression = progressionDao.findByPersonIdAndStoryId(personId, storyId);
        if (optionalProgression.isEmpty()) {
            throw new ProgressionNotFoundException("Aucune progression avec le personId " + personId + " et le storyId " + storyId);
        }

        Progression progression = optionalProgression.get();

        // TODO: vérifier qu'on a le droit d'avancer sur cette page depuis la page précédente
        progression.setActualPageId(pageId);

        return progressionDao.save(progression);
    }

    public List<Progression> findByPersonId(final Integer personId) {
        Assert.notNull(personId, "The personId parameter is mandatory !");
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format("La personne avec l'id {} n'existe pas", personId).getMessage());
        }

        return progressionDao.findByPersonId(personId);
    }

    private List<Progression> emptyProgressionList(final Integer personId, final Throwable e) {
        return Collections.emptyList();
    }

    public Optional<Progression> findByPersonIdAndStoryId(final Integer personId, final Integer storyId) {
        Assert.notNull(personId, "The personId parameter is mandatory !");
        Assert.notNull(storyId, "The storyId parameter is mandatory !");
        if (personService.notExists(personId)) {
            throw new PersonNotFoundException(MessageFormatter.format("La personne avec l'id {} n'existe pas", personId).getMessage());
        }
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format("L'histoire avec l'id {} n'existe pas", storyId).getMessage());
        }

        return progressionDao.findByPersonIdAndStoryId(personId, storyId);
    }

    private Optional<Page> emptyProgression(final Integer personId, final Integer storyId, final Throwable e) {
        return Optional.empty();
    }

    public Long countByStoryId(final Integer storyId) {
        Assert.notNull(storyId, "The storyId parameter is mandatory !");
        if (storyService.notExists(storyId)) {
            throw new StoryNotFoundException(MessageFormatter.format("L'histoire avec l'id {} n'existe pas", storyId).getMessage());
        }

        return progressionDao.countByStoryId(storyId);
    }

    private Long emptyProgressionCount(final Integer storyId, final Throwable e) {
        return 0L;
    }
}
