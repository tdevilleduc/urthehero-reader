package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "progression")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @NotNull
    private Integer storyId;
    @NotNull
    private Integer personId;
    @NotNull
    private Integer actualPageId;

}
