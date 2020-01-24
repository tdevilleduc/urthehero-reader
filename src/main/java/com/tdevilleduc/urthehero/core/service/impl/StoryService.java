package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.convertor.StoryConvertor;
import com.tdevilleduc.urthehero.core.dao.StoryDao;
import com.tdevilleduc.urthehero.core.exceptions.StoryNotFoundException;
import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;
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

    private StoryDao storyDao;
    @Autowired
    private StoryConvertor storyConvertor;

    public StoryService(StoryDao storyDao) {
        this.storyDao = storyDao;
    }

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
