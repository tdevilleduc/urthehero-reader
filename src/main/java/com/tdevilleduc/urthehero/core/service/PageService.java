package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.dao.PageDao;
import com.tdevilleduc.urthehero.core.model.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class PageService {

    @Inject
    PageDao pageDao;

    public Optional<Page> findById(final Integer pageId) {
        return pageDao.findById(pageId);
    }
}
