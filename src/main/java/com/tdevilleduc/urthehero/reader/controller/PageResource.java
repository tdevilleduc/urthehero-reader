package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.service.IPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/page")
public class PageResource {

    @Inject
    IPageService pageService;

    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Page> getPageById(@PathVariable Integer pageId) {
        if (log.isInfoEnabled()) {
            log.info("call: /api/page/{}", pageId);
        }
        Optional<Page> optional = pageService.findById(pageId);
        if (optional.isPresent()) {
            log.info("optional : {}", optional.get().toString());
        } else {
            log.info("DTC");
        }
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
