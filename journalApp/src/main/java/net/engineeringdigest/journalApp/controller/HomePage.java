package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home Page")
public class HomePage {

    @GetMapping("/")
    public String homePage() {
        return "Welcome to Journal Application";
    }
}
