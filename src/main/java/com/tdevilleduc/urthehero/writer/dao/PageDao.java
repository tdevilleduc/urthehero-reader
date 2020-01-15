package com.tdevilleduc.urthehero.writer.dao;

import com.tdevilleduc.urthehero.writer.model.Page;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PageDao extends Repository<Page, Integer> {

    Optional<Page> findById(Integer pageId);

}
