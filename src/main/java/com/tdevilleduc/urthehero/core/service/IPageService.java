package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public interface IPageService {

    boolean exists(Integer pageId);
    boolean notExists(Integer pageId);
    Optional<Page> findById(Integer pageId);
    List<Page> findAll();
    PageDTO createOrUpdate(PageDTO pageDto);
    void delete(Integer pageId);
}
