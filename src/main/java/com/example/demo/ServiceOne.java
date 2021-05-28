package com.example.demo;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ServiceOne {

    public String hello(){
        return "Hello";
    }
}
