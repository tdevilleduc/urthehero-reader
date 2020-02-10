package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.convertor.PageConvertor;
import com.tdevilleduc.urthehero.core.dao.PageDao;
import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import com.tdevilleduc.urthehero.core.service.IPageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.CHECK_PAGEID_PARAMETER_MANDATORY;
import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.ERROR_MESSAGE_PAGE_DOESNOT_EXIST;

@Slf4j
@Service
public class PageService implements IPageService {

    private PageDao pageDao;
    private PageConvertor pageConvertor;

    public PageService(PageDao pageDao, PageConvertor pageConvertor) {
        this.pageDao = pageDao;
        this.pageConvertor = pageConvertor;
    }

    public boolean exists(final Integer pageId) {
        Assert.notNull(pageId, CHECK_PAGEID_PARAMETER_MANDATORY);
        Optional<Page> page = pageDao.findById(pageId);
        if (!page.isPresent()) {
            log.error(ERROR_MESSAGE_PAGE_DOESNOT_EXIST, pageId);
            return false;
        }
        return true;
    }

    public boolean notExists(final Integer pageId) {
        return ! exists(pageId);
    }

    public Page findById(final Integer pageId) {
        Assert.notNull(pageId, CHECK_PAGEID_PARAMETER_MANDATORY);
        Optional<Page> optionalPage = pageDao.findById(pageId);
        if (!optionalPage.isPresent()) {
            throw new PageNotFoundException(MessageFormatter.format(ERROR_MESSAGE_PAGE_DOESNOT_EXIST, pageId).getMessage());
        }
        return optionalPage.get();
    }

    public List<Page> findAll() {
        return pageDao.findAll();
    }

    public PageDTO createOrUpdate(PageDTO pageDTO) {
        Page page = pageConvertor.convertToEntity(pageDTO);
        return pageConvertor.convertToDto(pageDao.save(page));
    }

    public void delete(Integer pageId) {
        Assert.notNull(pageId, CHECK_PAGEID_PARAMETER_MANDATORY);
        pageDao.delete(findById(pageId));
    }
}
