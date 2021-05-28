package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTwo {

    @GetMapping("/world")
    ResponseEntity<String> getHello(){
        return ResponseEntity.ok("World");
    }
}
