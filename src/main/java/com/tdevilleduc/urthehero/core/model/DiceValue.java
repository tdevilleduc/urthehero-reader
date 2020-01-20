package com.tdevilleduc.urthehero.core.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DiceValue {

    @NotNull
    private Integer value;
    @NotNull
    private Dice dice;

}
