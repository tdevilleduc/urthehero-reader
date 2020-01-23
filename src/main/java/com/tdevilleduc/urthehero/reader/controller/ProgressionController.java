package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.model.Progression;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import com.tdevilleduc.urthehero.core.service.IProgressionService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/progression")
public class ProgressionController {

    private final IProgressionService progressionService;
    private final IStoryService storyService;
    private final IPersonService personService;

    public ProgressionController(IProgressionService progressionService, IStoryService storyService, IPersonService personService) {
        this.progressionService = progressionService;
        this.storyService = storyService;
        this.personService = personService;
    }

    @GetMapping(value="/person/{personId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Progression>> getAllByPersonId(@PathVariable Integer personId) {
        if (personService.notExists(personId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(progressionService.findByPersonId(personId));
    }

    @GetMapping(value="/person/{personId}/story/{storyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Progression> getOneByPersonIdAndStoryId(@PathVariable Integer personId,
                                                           @PathVariable Integer storyId) {
        if (personService.notExists(personId)) {
            return ResponseEntity.notFound().build();
        }

        if (storyService.notExists(storyId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Progression> progression = progressionService.findByPersonIdAndStoryId(personId, storyId);
        if (progression.isPresent()) {

            return ResponseEntity.ok(progression.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="/person/{personId}/story/{storyId}/page/{newPageId}")
    public @ResponseBody
    ResponseEntity<Progression> postProgressionAction(@PathVariable Integer personId,
                                                      @PathVariable Integer storyId,
                                                      @PathVariable Integer newPageId) {
        return ResponseEntity.ok(progressionService.doProgressionAction(personId, storyId, newPageId));
    }


}