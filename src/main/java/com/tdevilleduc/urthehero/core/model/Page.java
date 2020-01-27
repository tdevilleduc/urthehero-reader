package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

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
    private Integer id;
    @NonNull
    private String text;
    @NonNull
    private String image;

    @Transient
    private List<NextPage> nextPageList = Collections.emptyList();
}

