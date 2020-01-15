package com.tdevilleduc.urthehero.writer.service;

import com.tdevilleduc.urthehero.writer.dao.PageDao;
import com.tdevilleduc.urthehero.writer.model.Page;

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
