package com.tdevilleduc.urthehero.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoryDTO {

    private Integer storyId;
    private Integer authorId;
    private Integer firstPageId;
    private String title;
    private String detailedText;
    private String image;

    public StoryDTO(Integer authorId, Integer firstPageId, String title, String detailedText, String image) {
        this.authorId = authorId;
        this.firstPageId = firstPageId;
        this.title = title;
        this.detailedText = detailedText;
        this.image = image;
    }

    @Override
    public String toString() {
        return "StoryDTO{" +
                "title='" + title + '\'' +
                ", authorId=" + authorId +
                ", firstPageId=" + firstPageId +
                ", detailedText='" + detailedText + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
