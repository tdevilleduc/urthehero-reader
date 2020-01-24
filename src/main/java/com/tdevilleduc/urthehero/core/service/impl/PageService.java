package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.convertor.PageConvertor;
import com.tdevilleduc.urthehero.core.dao.PageDao;
import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import com.tdevilleduc.urthehero.core.service.IPageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PageService implements IPageService {

    @Autowired
    PageDao pageDao;
    @Autowired
    private PageConvertor pageConvertor;

    public boolean exists(final Integer pageId) {
        Assert.notNull(pageId, "The pageId parameter is mandatory !");
        Optional<Page> page = pageDao.findById(pageId);
        if (page.isEmpty()) {
            log.error("La page avec l'id {} n'existe pas", pageId);
            return false;
        }
        return true;
    }

    public boolean notExists(final Integer pageId) {
        return ! exists(pageId);
    }

    public Optional<Page> findById(final Integer pageId) {
        Assert.notNull(pageId, "The pageId parameter is mandatory !");
        return pageDao.findById(pageId);
    }

    public List<Page> findAll() {
        return pageDao.findAll();
    }

    public PageDTO createOrUpdate(PageDTO pageDTO) {
        Page page = pageConvertor.convertToEntity(pageDTO);
        return pageConvertor.convertToDto(pageDao.save(page));
    }

    public void delete(Integer pageId) {
        Assert.notNull(pageId, "The pageId parameter is mandatory !");
        Optional<Page> optional = findById(pageId);
        optional
                .ifPresentOrElse(page -> pageDao.delete(page),
                        () -> {
                            throw new PageNotFoundException(MessageFormatter.format("La page avec l'id {} n'existe pas", pageId).getMessage());
                        }
                );
    }
}
