package com.tdevilleduc.urthehero.core.convertor;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageConvertor {

    public PageDTO convertToDto(Page page) {
        PageDTO pageDto = new PageDTO();
        pageDto.setId(page.getId());
        pageDto.setImage(page.getImage());
        pageDto.setText(page.getText());
        return pageDto;
    }

    public Page convertToEntity(PageDTO pageDto) {
        Page page = new Page();
        page.setId(pageDto.getId());
        page.setImage(pageDto.getImage());
        page.setText(pageDto.getText());
        return page;
    }
}
