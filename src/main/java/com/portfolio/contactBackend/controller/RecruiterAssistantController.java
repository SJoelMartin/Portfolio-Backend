package com.portfolio.contactBackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ChatResponse ask(@RequestBody ChatRequest request) throws IOException {
    	String answer =
                geminiService.askGemini(request.getMessage());

        return new ChatResponse(answer);
    }
}