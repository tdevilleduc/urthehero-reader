package com.tdevilleduc.urthehero.core.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PageDTO implements Serializable {

    private Integer id;
    private String text;
    private String image;}
