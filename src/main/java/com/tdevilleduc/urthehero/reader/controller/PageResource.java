package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.model.Page;
import com.tdevilleduc.urthehero.core.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
@Path("/api/page")
public class PageResource {

    @Inject
    PageService pageService;

//    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{pageId}")
    public @ResponseBody
    Callable<ResponseEntity<Page>> getPageById(@PathParam("pageId") Integer pageId) {
        return () -> {
            if (log.isInfoEnabled()) {
                log.info("call: /api/page/{}", pageId);
            }
            Optional<Page> optional = pageService.findById(pageId);
            return optional
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        };
    }
}
