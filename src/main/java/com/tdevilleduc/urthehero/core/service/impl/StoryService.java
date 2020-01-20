package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.StoryDao;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.model.Progression;
import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.service.IProgressionService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class StoryService implements IStoryService {

//    @Autowired
//    private IProgressionService progressionService;
    @Autowired
    StoryDao storyDao;

    public boolean exists(final Integer storyId) {
        Assert.notNull(storyId, "The story parameter is mandatory !");
        Optional<Story> story = storyDao.findById(storyId);
        if (story.isEmpty()) {
            log.error("L'histoire avec l'id {} n'existe pas", storyId);
            return false;
        }
        return true;
    }

    public boolean notExists(final Integer storyId) {
        return ! exists(storyId);
    }

    public Optional<Story> findById(final Integer storyId) {
        Assert.notNull(storyId, "The storyId parameter is mandatory !");
        return storyDao.findById(storyId);
//                .map(this::fillStoryWithNumberOfReaders);
    }

    private Optional<Story> emptyStory(final Integer storyId, final Throwable e) {
        log.error("Cannot retrieve story");
        return Optional.empty();
    }

    public List<Story> findAll() {
        return storyDao.findAll().stream()
//                .map(this::fillStoryWithNumberOfReaders)
                .collect(Collectors.toList());
    }

//    public List<Story> findByPersonId(final Integer personId) {
//        Assert.notNull(personId, "The personId parameter is mandatory !");
//        List<Progression> progressionList = progressionService.findByPersonId(personId);
//        return progressionList.stream()
//                .map(this::getStoryFromProgression)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(this::fillStoryWithNumberOfReaders)
//                .collect(Collectors.toList());
//    }

    private Optional<Story> getStoryFromProgression(Progression progression) {
        Optional<Story> optionalStory = storyDao.findById(progression.getStoryId());
        optionalStory.ifPresent(story -> story.setCurrentPageId(progression.getActualPageId()));
        return optionalStory;
    }

//    private Story fillStoryWithNumberOfReaders(Story story) {
//        Long numberOfReaders = progressionService.countByStoryId(story.getStoryId());
//        story.setNumberOfReaders(numberOfReaders);
//        return story;
//    }

    public Story createOrUpdate(Story story) {
        return storyDao.save(story);
    }

    public void delete(Integer storyId) {
        Assert.notNull(storyId, "The storyId parameter is mandatory !");
        Optional<Story> optional = findById(storyId);
        optional
            .ifPresentOrElse(story -> storyDao.delete(story),
                () -> {
                    throw new StoryNotFoundException(MessageFormatter.format("L'histoire avec l'id {} n'existe pas", storyId).getMessage());
                }
        );
    }
}
