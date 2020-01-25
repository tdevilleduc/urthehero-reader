package com.tdevilleduc.urthehero.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO {

    private Integer id;
    private String text;
    private String image;

    public PageDTO(String text, String image) {
        this.text = text;
        this.image = image;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
