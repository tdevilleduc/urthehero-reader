package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.model.Dice;
import com.tdevilleduc.urthehero.core.model.DiceValue;
import com.tdevilleduc.urthehero.core.service.IDiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dice")
public class DiceController {

    private final IDiceService diceService;

    public DiceController(IDiceService diceService) {
        this.diceService = diceService;
    }

    @GetMapping(value = "/roll/{dice}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<DiceValue> roll(@PathVariable(name = "dice") Dice dice) {
        Assert.notNull(dice, "The dice parameter is mandatory !");
        return ResponseEntity.ok(diceService.roll(dice));
    }

    @GetMapping(value = "/roll/{dice}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<DiceValue>> roll(@PathVariable(name = "dice") Dice dice,
                                         @PathVariable(name = "count") Integer count) {
        Assert.notNull(dice, "The dice parameter is mandatory !");
        Assert.notNull(count, "The dice parameter is mandatory !");
        List<DiceValue> diceValues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            diceValues.add(diceService.roll(dice));
        }

        return ResponseEntity.ok(diceValues);
    }

}
