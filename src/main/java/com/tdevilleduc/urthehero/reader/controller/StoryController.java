package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.exceptions.StoryInternalErrorException;
import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.model.dto.StoryDTO;
import com.tdevilleduc.urthehero.core.service.IPageService;
import com.tdevilleduc.urthehero.core.service.IPersonService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.ERROR_MESSAGE_PAGE_DOESNOT_EXIST;
import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.ERROR_MESSAGE_PERSON_DOESNOT_EXIST;

@Slf4j
@RestController
@RequestMapping("/api/story")
public class StoryController {

    private final IStoryService storyService;
    private final IPersonService personService;
    private final IPageService pageService;

    public StoryController(IStoryService storyService, IPersonService personService, IPageService pageService) {
        this.storyService = storyService;
        this.personService = personService;
        this.pageService = pageService;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Story>> getAllStories() {
        return ResponseEntity.ok(storyService.findAll());
    }

    @GetMapping(value = "/{storyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Story> getStoryById(@PathVariable(name = "storyId") Integer storyId) {
        Optional<Story> optional = this.storyService.findById(storyId);
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity<StoryDTO> createStory(@RequestBody StoryDTO storyDto) {
        //TODO: déplacer les controles dans le service ?
        Assert.notNull(storyDto.getAuthorId(), () -> {
            throw new StoryInternalErrorException("L'auteur de l'histoire passée en paramètre ne peut pas être null");
        });
        Assert.notNull(storyDto.getFirstPageId(), () -> {
            throw new StoryInternalErrorException("La première page de l'histoire passée en paramètre ne peut pas être null");
        });
        if (personService.notExists(storyDto.getAuthorId())) {
            throw new StoryInternalErrorException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, storyDto.getAuthorId()).getMessage());
        }
        if (pageService.notExists(storyDto.getFirstPageId())) {
            throw new StoryInternalErrorException(MessageFormatter.format(ERROR_MESSAGE_PAGE_DOESNOT_EXIST, storyDto.getFirstPageId()).getMessage());
        }
        if (storyDto.getStoryId() != null && storyService.exists(storyDto.getStoryId())) {
            throw new StoryInternalErrorException(MessageFormatter.format("L'id {} existe déjà. Elle ne peut être créée", storyDto.getStoryId()).getMessage());
        }
        return ResponseEntity.ok(storyService.createOrUpdate(storyDto));
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<StoryDTO> updateStory(@RequestBody StoryDTO storyDto) {
        //TODO: déplacer les controles dans le service ?
        Assert.notNull(storyDto.getAuthorId(), () -> {
            throw new StoryInternalErrorException("L'auteur de l'histoire passée en paramètre ne peut pas être null");
        });
        Assert.notNull(storyDto.getFirstPageId(), () -> {
            throw new StoryInternalErrorException("La première page de l'histoire passée en paramètre ne peut pas être null");
        });
        Assert.notNull(storyDto.getStoryId(), () -> {
            throw new StoryInternalErrorException("L'identifiant de l'histoire passée en paramètre ne peut pas être null");
        });
        if (personService.notExists(storyDto.getAuthorId())) {
            throw new StoryInternalErrorException(MessageFormatter.format(ERROR_MESSAGE_PERSON_DOESNOT_EXIST, storyDto.getAuthorId()).getMessage());
        }
        if (pageService.notExists(storyDto.getFirstPageId())) {
            throw new StoryInternalErrorException(MessageFormatter.format(ERROR_MESSAGE_PAGE_DOESNOT_EXIST, storyDto.getFirstPageId()).getMessage());
        }
        if (storyService.notExists(storyDto.getStoryId())) {
            throw new StoryInternalErrorException(MessageFormatter.format("L'id {} n'existe pas", storyDto.getStoryId()).getMessage());
        }
        return ResponseEntity.ok(storyService.createOrUpdate(storyDto));
    }

    @DeleteMapping(value = "/{storyId}")
    public @ResponseBody
    void deleteStory(@PathVariable(name = "storyId") Integer storyId) {
        storyService.delete(storyId);
    }
}
