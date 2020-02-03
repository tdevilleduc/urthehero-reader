package com.tdevilleduc.urthehero.reader.controller;

import com.tdevilleduc.urthehero.core.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import static com.tdevilleduc.urthehero.core.constant.ApplicationConstants.CHECK_NAME_PARAMETER_MANDATORY;

@Slf4j
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> hello(@PathVariable(name = "name") String name) {
        Assert.notNull(name, CHECK_NAME_PARAMETER_MANDATORY);
        return ResponseEntity.ok(helloService.hello(name));
    }

    @GetMapping(value = "/polite/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> politeHello(@PathVariable(name = "name") String name) {
        Assert.notNull(name, CHECK_NAME_PARAMETER_MANDATORY);
        return ResponseEntity.ok(helloService.politeHello(name));
    }
}
