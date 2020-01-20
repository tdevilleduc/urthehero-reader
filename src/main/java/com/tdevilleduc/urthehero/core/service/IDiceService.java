package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.Dice;
import com.tdevilleduc.urthehero.core.model.DiceValue;

public interface IDiceService {

    /**
     * Effectue un lancer de dés avec le dé dice
     * @param dice le type de dé à lancer
     * @return la valeur du dé après lancé
     */
    DiceValue roll(Dice dice);

}
