package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "page")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    @NonNull
    private String text;
    @NonNull
    private String image;

}

