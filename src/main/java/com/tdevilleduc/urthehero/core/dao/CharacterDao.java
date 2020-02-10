package com.tdevilleduc.urthehero.core.dao;

import com.tdevilleduc.urthehero.core.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterDao extends JpaRepository<Character, Integer> {

    List<Character> findByPersonId(Integer personId);
    Character findByPersonIdAndStoryId(Integer personId, Integer storyId);
}
