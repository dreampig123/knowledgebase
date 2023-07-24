package org.pegcode.web.controller;

import org.pegcode.core.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class EsServiceController {

    @Autowired
    private EsService esService;

    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/index/create")
    public void createIndex() {
        try {
            esService.createIndex("user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/index/get")
    public void getIndex() {
        try {
            esService.getIndex("user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/index/exists")
    public void existsIndex() {
        try {
            esService.existsIndex("user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/data/insert")
    public void insertUser() {
        try {
            esService.userInsert();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
