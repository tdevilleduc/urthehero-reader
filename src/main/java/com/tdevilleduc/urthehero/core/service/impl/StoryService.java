package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.convertor.StoryConvertor;
import com.tdevilleduc.urthehero.core.dao.StoryDao;
import com.tdevilleduc.urthehero.core.exceptions.PersonNotFoundException;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.*;


@Slf4j
@Service
public class StoryService implements IStoryService {

    private StoryDao storyDao;
    private StoryConvertor storyConvertor;

    public StoryService(StoryDao storyDao, StoryConvertor storyConvertor) {
        this.storyDao = storyDao;
        this.storyConvertor = storyConvertor;
    }

    public boolean exists(final Integer storyId) {
        Assert.notNull(storyId, "The story parameter is mandatory !");
        Optional<Story> story = storyDao.findById(storyId);
        if (!story.isPresent()) {
            log.error(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId);
            return false;
        }
        return true;
    }

    public boolean notExists(final Integer storyId) {
        return ! exists(storyId);
    }

    public Optional<Story> findById(final Integer storyId) {
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        return storyDao.findById(storyId);
    }

    public List<Story> findAll() {
        return storyDao.findAll().stream()
                .collect(Collectors.toList());
    }

    public StoryDTO createOrUpdate(StoryDTO storyDto) {
        Story story = storyConvertor.convertToEntity(storyDto);
        return storyConvertor.convertToDto(storyDao.save(story));
    }

    public void delete(Integer storyId) {
        Assert.notNull(storyId, CHECK_STORYID_PARAMETER_MANDATORY);
        Optional<Story> optional = findById(storyId);
        if (optional.isPresent()) {
            storyDao.delete(optional.get());
        } else {
            throw new StoryNotFoundException(MessageFormatter.format(ERROR_MESSAGE_STORY_DOESNOT_EXIST, storyId).getMessage());
        }
    }
}
