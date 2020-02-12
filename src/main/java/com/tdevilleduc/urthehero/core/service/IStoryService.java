package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;

import java.util.List;

public interface IStoryService {

    boolean exists(Integer storyId);
    boolean notExists(Integer storyId);
    Story findById(Integer storyId);
    List<Story> findAll();
    StoryDTO createOrUpdate(StoryDTO storyDto);
    void delete(Integer storyId);
}
