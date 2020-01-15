package com.tdevilleduc.urthehero.writer.controller;

import com.tdevilleduc.urthehero.writer.model.Page;
import com.tdevilleduc.urthehero.writer.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
@Path("/api/page")
public class PageResource {

    @Inject
    PageService pageService;

    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Callable<ResponseEntity<Page>> getPageById(HttpServletRequest request, @PathParam("pageId") Integer pageId) {
        return () -> {
            if (log.isInfoEnabled()) {
                log.info("call: {}", request.getRequestURI());
            }
            Optional<Page> optional = pageService.findById(pageId);
            return optional
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };
    }
}
