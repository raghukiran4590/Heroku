package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health check APIs")
public class HealthCheck {

@GetMapping("/api/health-check")
    public String healthCheck() {
        return "Ok";
    }
}
