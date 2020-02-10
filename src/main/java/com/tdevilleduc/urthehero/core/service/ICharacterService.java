package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Character;

import java.util.List;

public interface ICharacterService {
    List<Integer> findStoryByPersonId(final Integer personId);
    Character updateLife(Integer personId, Integer storyId, Integer changeLife);
    Character changePage(Integer personId, Integer storyId, Integer newPageId);

}
