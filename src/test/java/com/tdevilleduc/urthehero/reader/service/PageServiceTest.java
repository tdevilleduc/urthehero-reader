package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.service.impl.PageService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
public class PageServiceTest extends AbstractTest {

    @Autowired
    PageService pageService;

    private static final Integer pageId_exists = 1;
    private static final Integer pageId_notExists = 123456789;

    @Test
    public void exists_thenSuccess() {
        boolean exists = pageService.exists(pageId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = pageService.exists(pageId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenSuccess() {
        boolean notExists = pageService.notExists(pageId_notExists);
        Assertions.assertTrue(notExists);
    }
}
