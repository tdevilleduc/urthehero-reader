package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "character")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;
    @NonNull
    private Integer storyId;
    @NonNull
    private Integer personId;
    @NonNull
    private Integer pageId;
    @NonNull
    private String displayName;
    @NonNull
    private Integer lifePoints;

    private String image;
}
