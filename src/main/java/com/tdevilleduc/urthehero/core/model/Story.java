package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "story")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storyId;
    @NonNull
    private String title;
    @NonNull
    private Integer authorId;
    @NonNull
    private Integer firstPageId;
    @NonNull
    private String detailedText;
    @NonNull
    private String image;

    @Transient
    private Integer currentPageId;
    @Transient
    private Long numberOfReaders;



}
