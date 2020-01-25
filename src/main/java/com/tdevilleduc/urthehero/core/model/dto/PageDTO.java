package com.tdevilleduc.urthehero.core.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
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
