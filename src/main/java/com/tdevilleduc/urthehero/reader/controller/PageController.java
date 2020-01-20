package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.exceptions.PageInternalErrorException;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.Story;
import com.tdevilleduc.urthehero.core.service.IPageService;
import com.tdevilleduc.urthehero.core.service.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
@RestController
@RequestMapping("/api/page")
public class PageController {

//    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody
//    ResponseEntity<Page> getPageById(@PathVariable(name = "pageId") Integer pageId) {
//        if (log.isInfoEnabled()) {
//            log.info("call: /api/page/{}", pageId);
//        }
//        Optional<Page> optional = pageService.findById(pageId);
//        return optional
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    private final IStoryService storyService;
    private final IPageService pageService;

    public PageController(IStoryService storyService, IPageService pageService) {
        this.storyService = storyService;
        this.pageService = pageService;
    }

    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Callable<ResponseEntity<Page>> getPageById(@PathVariable Integer pageId) {
        return () -> {
            Optional<Page> optional = pageService.findById(pageId);
            return optional
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };
    }

    @PutMapping
    public @ResponseBody Callable<ResponseEntity<Page>> createPage(@RequestBody @NotNull Page page) {
        return () -> {
            if (page.getId() != null && pageService.exists(page.getId())) {
                throw new PageInternalErrorException(MessageFormatter.format("Une page avec l'identifiant {} existe déjà. Elle ne peut être créée", page.getId()).getMessage());
            }
            return ResponseEntity.ok(pageService.createOrUpdate(page));
        };
    }

    @PostMapping
    public @ResponseBody Callable<ResponseEntity<Page>> updatePage(@RequestBody @NotNull Page page) {
        return () -> {
            Assert.notNull(page.getId(), () -> {
                throw new PageInternalErrorException("L'identifiant de la page passée en paramètre ne peut pas être null");
            });
            return ResponseEntity.ok(pageService.createOrUpdate(page));
        };
    }

    @DeleteMapping(value = "/{pageId}")
    public @ResponseBody void deletePage(@PathVariable @NotNull Integer pageId) {
        pageService.delete(pageId);
    }
}
