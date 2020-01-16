package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.PageDao;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.service.IPageService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class PageService implements IPageService {

    @Inject
    PageDao pageDao;

    public Optional<Page> findById(final Integer pageId) {
        Assert.notNull(pageId, "The pageId parameter is mandatory !");
        return pageDao.findById(pageId);
    }
}
