package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import com.tdevilleduc.urthehero.core.service.impl.PageService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@QuarkusTest
public class PageServiceTest extends AbstractTest {

    @Autowired
    PageService pageService;

    private static final Integer pageId_exists = 1;
    private static final Integer pageId_notExists = 123456789;

    @Test
    public void exists_thenFound() {
        boolean exists = pageService.exists(pageId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = pageService.exists(pageId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenFound() {
        boolean notExists = pageService.notExists(pageId_exists);
        Assertions.assertFalse(notExists);
    }

    @Test
    public void notExists_thenNotFound() {
        boolean notExists = pageService.notExists(pageId_notExists);
        Assertions.assertTrue(notExists);
    }

    @Test
    public void findAll_thenSuccess() {
        List<Page> listPage = pageService.findAll();
        Assertions.assertNotNull(listPage);
        Assertions.assertEquals(8, listPage.size());
    }

    @Test
    public void findById_thenSuccess() {
        Optional<Page> optionalPage = pageService.findById(pageId_exists);
        Assertions.assertNotNull(optionalPage);
        Assertions.assertTrue(optionalPage.isPresent());
        Page page = optionalPage.get();
        Assertions.assertNotNull(page);
        Assertions.assertEquals("Ulysse", page.getText());
        Assertions.assertEquals("image3", page.getImage());
    }

    @Test
    public void create_thenSuccess() {
        String test = new RandomDataGenerator().nextHexString(20);
        String image = new RandomDataGenerator().nextHexString(20);

        PageDTO originPageDto = new PageDTO(test, image);
        PageDTO pageDto = pageService.createOrUpdate(originPageDto);
        Assertions.assertNotNull(pageDto);
        Assertions.assertEquals(originPageDto.toString(), pageDto.toString());
    }
}
