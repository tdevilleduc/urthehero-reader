package com.tdevilleduc.urthehero.core.dao;

import com.tdevilleduc.urthehero.core.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryDao extends JpaRepository<Story, Integer> {

}
