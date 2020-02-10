package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.service.ICharacterService;
import com.tdevilleduc.urthehero.core.service.IPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/play")
public class PlayController {
    @Autowired
    IPageService pageService;
    @Autowired
    ICharacterService characterService;

    @PostMapping(value="/person/{personId}/story/{storyId}/page/{newPageId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Page> postProgressionAction(@PathVariable(name = "personId") Integer personId,
                                               @PathVariable(name = "storyId") Integer storyId,
                                               @PathVariable(name = "newPageId") Integer newPageId) {
        characterService.changePage(personId, storyId, newPageId);

        return ResponseEntity.ok(pageService.findById(newPageId));
    }

}
