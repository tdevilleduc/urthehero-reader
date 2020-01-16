package com.tdevilleduc.urthehero.core.dao;

import com.tdevilleduc.urthehero.core.model.Page;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PageDao extends Repository<Page, Integer> {

    Optional<Page> findById(Integer pageId);

}
