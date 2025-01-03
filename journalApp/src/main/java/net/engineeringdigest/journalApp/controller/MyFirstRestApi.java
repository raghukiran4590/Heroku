package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstRestApi {

    @GetMapping("/api/welcome")
    public String getMessage() {
        return "Hello World!";
    }
}
