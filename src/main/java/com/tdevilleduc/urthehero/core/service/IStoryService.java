package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Story;

import java.util.List;
import java.util.Optional;

public interface IStoryService {

    boolean exists(Integer storyId);
    boolean notExists(Integer storyId);
    Optional<Story> findById(Integer storyId);
    List<Story> findAll();
    Story createOrUpdate(Story story);
    void delete(Integer storyId);
}
