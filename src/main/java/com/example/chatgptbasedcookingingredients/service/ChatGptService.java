package com.example.chatgptbasedcookingingredients.service;

import com.example.chatgptbasedcookingingredients.model.ChatGPTRequest;
import com.example.chatgptbasedcookingingredients.model.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ChatGptService {
    private final RestClient client;

    public ChatGptService(
            @Value("${open.ai.key}") String apikey,
            @Value("${open.ai.url}") String baseurl){
        client = RestClient.builder()
                .baseUrl(baseurl)
                .defaultHeader("Authorization", "Bearer " + apikey) //<- Header mit Metainformationen. z. B. Unser Auth Token!
                .build();
    }

    public String askQuestion(String q) {
        String question = "(I want the answer as just one word): Is " + q + " vegan, vegetarian or regular?";
        ChatGPTRequest request = new ChatGPTRequest(question);
        ChatGPTResponse response = client.post()
                .body(request)
                .retrieve()
                .body(ChatGPTResponse.class);
        return response.getAnswer();
    }

}
