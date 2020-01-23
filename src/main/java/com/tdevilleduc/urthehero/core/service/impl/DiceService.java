package com.tdevilleduc.urthehero.core.service.impl;

import com.tdevilleduc.urthehero.core.model.Dice;
import com.tdevilleduc.urthehero.core.model.DiceValue;
import com.tdevilleduc.urthehero.core.service.IDiceService;
import org.springframework.stereotype.Service;

@Service
public class DiceService implements IDiceService {

    @Override
    public DiceValue roll(Dice dice) {
        return new DiceValue(generatingRandomIntegerBounded(dice.getValue()), dice);
    }

    private Integer generatingRandomIntegerBounded(Integer rightLimit) {
        // TODO recréer le comportement de géneration aléatoire de nombre
        return 1;
    }
}
