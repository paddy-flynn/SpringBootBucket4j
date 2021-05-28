package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class ControllerOne {

    private final ServiceOne serviceOne;

    public ControllerOne(ServiceOne so) {
        this.serviceOne = so;
    }

    @GetMapping("/")
    ResponseEntity<String> getHello() {
        return ResponseEntity.ok(this.serviceOne.hello());
    }
}
