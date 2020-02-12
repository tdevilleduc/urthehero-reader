package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;
import com.tdevilleduc.urthehero.core.service.impl.StoryService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@QuarkusTest
public class StoryServiceTest extends AbstractTest {

    @Autowired
    StoryService storyService;

    private static final Integer storyId_exists = 1;
    private static final Integer storyId_notExists = 123456789;

    @Test
    public void exists_thenFound() {
        boolean exists = storyService.exists(storyId_exists);
        Assertions.assertTrue(exists);
    }

    @Test
    public void exists_thenNotFound() {
        boolean exists = storyService.exists(storyId_notExists);
        Assertions.assertFalse(exists);
    }

    @Test
    public void notExists_thenFound() {
        boolean notExists = storyService.notExists(storyId_exists);
        Assertions.assertFalse(notExists);
    }

    @Test
    public void notExists_thenNotFound() {
        boolean notExists = storyService.notExists(storyId_notExists);
        Assertions.assertTrue(notExists);
    }

    @Test
    public void findAll_thenSuccess() {
        List<Story> listStory = storyService.findAll();
        Assertions.assertNotNull(listStory);
        Assertions.assertEquals(3, listStory.size());
    }

    @Test
    public void findById_thenSuccess() {
        Story story = storyService.findById(storyId_exists);
        Assertions.assertNotNull(story);
        Assertions.assertEquals(1, story.getAuthorId());
        Assertions.assertEquals(1, story.getFirstPageId());
        Assertions.assertEquals("Ulysse", story.getTitle());
        Assertions.assertEquals("blablabla Ulysse prenons un texte long pour decrire lhistoire", story.getDetailedText());
        Assertions.assertEquals("imageUlysse", story.getImage());
    }

    @Test
    public void create_thenSuccess() {

        Integer authorId = 1;
        Integer firstPageId = 1;
        String title = new RandomDataGenerator().nextHexString(20);
        String detailedText = new RandomDataGenerator().nextHexString(20);
        String image = new RandomDataGenerator().nextHexString(20);

        StoryDTO originStoryDto = new StoryDTO(authorId, firstPageId, title, detailedText, image);
        StoryDTO storyDto = storyService.createOrUpdate(originStoryDto);
        Assertions.assertNotNull(storyDto);
        Assertions.assertEquals(originStoryDto.toString(), storyDto.toString());
    }
}
