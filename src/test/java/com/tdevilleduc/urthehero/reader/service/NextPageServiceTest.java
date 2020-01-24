package com.tdevilleduc.urthehero.reader.service;

import com.tdevilleduc.urthehero.core.exceptions.PageNotFoundException;
import com.tdevilleduc.urthehero.core.model.NextPage;
import com.tdevilleduc.urthehero.core.model.Position;
import com.tdevilleduc.urthehero.core.service.impl.NextPageService;
import com.tdevilleduc.urthehero.reader.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@QuarkusTest
class NextPageServiceTest extends AbstractTest {

    @Autowired
    NextPageService nextPageService;

    @Test
    void test_findByPageId_thenCorrect() {
        Integer pageId = 1;
        List<NextPage> nextPageList = nextPageService.findByPageId(pageId);
        Assertions.assertNotNull(nextPageList);
        Assertions.assertFalse(nextPageList.isEmpty());
        Assertions.assertEquals(3, nextPageList.size());

        NextPage nextPage1 = nextPageList.get(0);
        Assertions.assertNotNull(nextPage1);
        Assertions.assertEquals(Integer.valueOf(1), nextPage1.getId());
        Assertions.assertEquals(Integer.valueOf(2), nextPage1.getDestinationPageId());
        Assertions.assertEquals(Integer.valueOf(1), nextPage1.getPageId());
        Assertions.assertEquals("gauche", nextPage1.getText());
        Assertions.assertEquals(Position.LEFT, nextPage1.getPosition());

        NextPage nextPage2 = nextPageList.get(1);
        Assertions.assertNotNull(nextPage2);
        Assertions.assertEquals(Integer.valueOf(2), nextPage2.getId());
        Assertions.assertEquals(Integer.valueOf(3), nextPage2.getDestinationPageId());
        Assertions.assertEquals(Integer.valueOf(1), nextPage2.getPageId());
        Assertions.assertEquals("droite", nextPage2.getText());
        Assertions.assertEquals(Position.RIGHT, nextPage2.getPosition());

        NextPage nextPage3 = nextPageList.get(2);
        Assertions.assertNotNull(nextPage3);
        Assertions.assertEquals(Integer.valueOf(3), nextPage3.getId());
        Assertions.assertEquals(Integer.valueOf(8), nextPage3.getDestinationPageId());
        Assertions.assertEquals(Integer.valueOf(1), nextPage3.getPageId());
        Assertions.assertEquals("centre", nextPage3.getText());
        Assertions.assertEquals(Position.CENTER, nextPage3.getPosition());

    }

    @Test
    void test_findByPageId_thenNotFound() {
        Integer pageId = 13;
        Assertions.assertThrows(PageNotFoundException.class, () -> nextPageService.findByPageId(pageId));
    }

}
