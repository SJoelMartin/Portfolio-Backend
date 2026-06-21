package com.portfolio.contactBackend.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.contactBackend.dto.ChatRequest;
import com.portfolio.contactBackend.dto.ChatResponse;
import com.portfolio.contactBackend.service.GeminiService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class RecruiterAssistantController {

    @Autowired
    private GeminiService geminiService;
    
    @PostMapping("/recruiter-assistant")
    public ResponseEntity<?> ask(@RequestBody ChatRequest request) {
        try {
            String response = geminiService.askGemini(request.getMessage());
            return ResponseEntity.ok(new ChatResponse(response));

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "error", e.getMessage()
                    ));
        }
    }
}