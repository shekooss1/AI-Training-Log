package com.example.spring_backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class healthController {
@GetMapping("/health")
public Map<String,String> health() {
        return Map.of("status", "UP");
}

}
