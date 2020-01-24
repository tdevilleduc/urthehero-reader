package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.exceptions.PageInternalErrorException;
import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.model.dto.PageDTO;
import com.tdevilleduc.urthehero.core.service.IPageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/page")
public class PageController {

    private final IPageService pageService;

    public PageController(IPageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Page> getPageById(@PathVariable(name = "pageId") Integer pageId) {
        Optional<Page> optional = pageService.findById(pageId);
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public @ResponseBody ResponseEntity<PageDTO> createPage(@RequestBody @NotNull PageDTO pageDto) {
        if (pageDto.getId() != null && pageService.exists(pageDto.getId())) {
            throw new PageInternalErrorException(MessageFormatter.format("Une page avec l'identifiant {} existe déjà. Elle ne peut être créée", pageDto.getId()).getMessage());
        }
        return ResponseEntity.ok(pageService.createOrUpdate(pageDto));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<PageDTO> updatePage(@RequestBody @NotNull PageDTO pageDto) {
        Assert.notNull(pageDto.getId(), () -> {
            throw new PageInternalErrorException("L'identifiant de la page passée en paramètre ne peut pas être null");
        });
        return ResponseEntity.ok(pageService.createOrUpdate(pageDto));
    }

    @DeleteMapping(value = "/{pageId}")
    public @ResponseBody void deletePage(@PathVariable @NotNull Integer pageId) {
        pageService.delete(pageId);
    }
}
