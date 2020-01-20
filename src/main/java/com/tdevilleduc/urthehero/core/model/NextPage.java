package com.tdevilleduc.urthehero.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "next_page")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NextPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @JsonIgnore
    private Integer id;
    @NotNull
    private String text;
    @NotNull
    private Integer pageId;
    @NotNull
    private Integer destinationPageId;
    @NotNull
    private Position position;

}
