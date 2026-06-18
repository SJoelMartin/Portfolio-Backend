package com.portfolio.contactBackend.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Autowired
    private PortfolioContextService portfolioContextService;
    
    public String askGemini(String question) throws IOException {
    	String context = portfolioContextService.getContext();

        String prompt = """
        		You are Joel's AI recruiter assistant.

        		Answer professionally.

        		Only use the information provided below.
        		Never hallucinate.
        		If information is unavailable say:
        		"I couldn't find that information in Joel's portfolio."

        		Portfolio Information:

        		""" + context +

        		"""

        		Recruiter's Question:
        		""" + question;
        
        // call Gemini API
        return callGemini(prompt);
    }

	private String callGemini(String prompt) {
	    try {
	        String url =
	                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
	                        + apiKey;
	        String requestBody = """
	        {
	          "contents":[
	            {
	              "parts":[
	                {
	                  "text":"%s"
	                }
	              ]
	            }
	          ]
	        }
	        """.formatted(prompt.replace("\"","\\\""));
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .header("Content-Type","application/json")
	                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
	                .build();
	        HttpClient client = HttpClient.newHttpClient();
	        HttpResponse<String> response =
	                client.send(request, HttpResponse.BodyHandlers.ofString());
	        return extractResponse(response.body());
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        return "Unable to process request.";
	    }
	}
	
	private String extractResponse(String json) {

	    JsonObject root =
	            JsonParser.parseString(json).getAsJsonObject();

	    return root
	            .getAsJsonArray("candidates")
	            .get(0)
	            .getAsJsonObject()
	            .getAsJsonObject("content")
	            .getAsJsonArray("parts")
	            .get(0)
	            .getAsJsonObject()
	            .get("text")
	            .getAsString();
	}
}