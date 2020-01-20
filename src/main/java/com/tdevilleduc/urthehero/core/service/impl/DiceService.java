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
//        return new RandomDataGenerator().nextInt(1, rightLimit);
        return 1;
    }
}
