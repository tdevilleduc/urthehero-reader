package com.tdevilleduc.urthehero.core.convertor;

import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoryConvertor {

    public StoryDTO convertToDto(Story story) {

        StoryDTO storyDto = new StoryDTO();
        storyDto.setStoryId(story.getStoryId());
        storyDto.setAuthorId(story.getAuthorId());
        storyDto.setDetailedText(story.getDetailedText());
        storyDto.setFirstPageId(story.getFirstPageId());
        storyDto.setImage(story.getImage());
        storyDto.setTitle(story.getTitle());
        return storyDto;
    }

    public Story convertToEntity(StoryDTO storyDto) {

        Story story = new Story();
        story.setStoryId(storyDto.getStoryId());
        story.setAuthorId(storyDto.getAuthorId());
        story.setDetailedText(storyDto.getDetailedText());
        story.setFirstPageId(storyDto.getFirstPageId());
        story.setImage(storyDto.getImage());
        story.setTitle(storyDto.getTitle());
        return story;
    }
}
