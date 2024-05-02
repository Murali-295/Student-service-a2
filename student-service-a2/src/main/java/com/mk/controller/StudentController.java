package com.mk.controller;

import com.mk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StudentController {

    @Autowired
   private StudentService service;

    @GetMapping("/json")
    public String readJson() throws IOException {
        return service.readJson();
    }
}
