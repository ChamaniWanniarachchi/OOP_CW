package com.example.demo;

// Importing Jackson's ObjectMapper for JSON serialization/deserialization
import com.fasterxml.jackson.databind.ObjectMapper;
// Importing Spring Web annotations for building REST APIs
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * The ConfigController class is a REST controller for managing
 * configurations of the ticketing system. It provides endpoints
 * to save and load configurations to/from a JSON file.
 */

@RestController // Marks this class as a Spring REST controller.
@RequestMapping("/api/config") // Base URL path for all endpoints in this controller.

public class ConfigController {
    // Path to the configuration file
    private static final String CONFIG_FILE = "config.json";
    // Jackson's ObjectMapper for handling JSON operations
    private final ObjectMapper objectMapper = new ObjectMapper();

    // HTTP POST endpoint for saving configuration.
    @PostMapping("/save")
    public String saveConfig(@RequestBody Configuration config) {
        try {
            // Serialize the configuration object and write it to the specified file.
            objectMapper.writeValue(new File(CONFIG_FILE), config);
            return "Configuration saved successfully.";
        } catch (IOException e) {
            return "Error saving configuration: " + e.getMessage();
        }
    }

    // HTTP GET endpoint for loading configuration.
    @GetMapping("/load")
    public Configuration loadConfig() {
        try {
            // Deserialize the JSON file content into a Configuration object.
            return objectMapper.readValue(new File(CONFIG_FILE), Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration: " + e.getMessage());
        }
    }
}
