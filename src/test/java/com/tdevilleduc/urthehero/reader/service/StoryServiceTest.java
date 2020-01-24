package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.service.impl.StoryService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
public class StoryServiceTest extends AbstractTest {

    @Autowired
    StoryService storyService;

    private static final Integer storyId_exists = 1;
    private static final Integer storyId_notExists = 123456789;

    @Test
    public void exists_thenSuccess() {
        boolean exists = storyService.exists(storyId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = storyService.exists(storyId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenSuccess() {
        boolean notExists = storyService.notExists(storyId_notExists);
        Assertions.assertTrue(notExists);
    }
}
