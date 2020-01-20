package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.dao.NextPageDao;
import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.model.NextPage;
import com.tdevilleduc.urthehero.core.service.INextPageService;
import com.tdevilleduc.urthehero.core.service.IPageService;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NextPageService implements INextPageService {

    @Autowired
    IPageService pageService;
    @Autowired
    NextPageDao nextPageDao;

    public List<NextPage> findByPageId(Integer pageId) {
        if (pageService.notExists(pageId)) {
            throw new PageNotFoundException(MessageFormatter.format("La page avec l'id {} n'existe pas", pageId).getMessage());
        }

        return nextPageDao.findByPageId(pageId);
    }
}
