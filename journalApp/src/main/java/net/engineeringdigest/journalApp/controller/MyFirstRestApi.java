package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstRestApi {

    @GetMapping("/api/welcome")
    @Tag(name = "Welcome API")
    public String getMessage() {
        return "Hello World!";
    }
}
