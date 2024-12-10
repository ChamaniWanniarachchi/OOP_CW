package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/config")

public class ConfigController {
    private static final String CONFIG_FILE = "config.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public String saveConfig(@RequestBody Configuration config) {
        try {
            objectMapper.writeValue(new File(CONFIG_FILE), config);
            return "Configuration saved successfully.";
        } catch (IOException e) {
            return "Error saving configuration: " + e.getMessage();
        }
    }

    @GetMapping("/load")
    public Configuration loadConfig() {
        try {
            return objectMapper.readValue(new File(CONFIG_FILE), Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration: " + e.getMessage());
        }
    }
}
