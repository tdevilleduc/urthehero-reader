package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public interface IPageService {

    boolean exists(Integer pageId);
    boolean notExists(Integer pageId);
    Optional<Page> findById(Integer pageId);
    List<Page> findAll();
    Page createOrUpdate(Page page);
    void delete(Integer pageId);
}
