package com.tdevilleduc.urthehero.core.convertor;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageConvertor {

    @Autowired
    ModelMapper modelMapper;

    public PageDTO convertToDto(Page page) {
        return modelMapper.map(page, PageDTO.class);
    }

    public Page convertToEntity(PageDTO pageDto) {
        return modelMapper.map(pageDto, Page.class);
    }
}
