package com.email.writer.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailGeneratorService { //Help us making api call to ai

    private final WebClient webClient;

    @Value("${groq.api.url}")
    private String groqApiUrl;

    @Value("${groq.api.key}")
    private String groqApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
        //Build the Prompt
        String prompt=buildPrompt(emailRequest);

        //craft request
        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(Map.of(
                        "role", "user",
                        "content", prompt
                ))
        );
        //Do request and get response -- (we are using WebClient it is built on top of reactor
        //and it enables us to handle asynchronous non-block http request

        String response= webClient.post()
                .uri(groqApiUrl)
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + groqApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Extract response and return response
        return extractResponseContent(response);

    }

    private String extractResponseContent(String response) {

        try {
            ObjectMapper mapper=new ObjectMapper();
            JsonNode rootNode=mapper.readTree(response);

            return rootNode.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
        }catch (Exception e){
            return "Error processing request: "+e.getMessage();
        }
    }


    //Prompt is crafted here
    private String buildPrompt(EmailRequest emailRequest) {

        StringBuilder prompt=new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate subject line ");

        if(emailRequest.getTone() !=null && !emailRequest.getTone().isEmpty() ){
            prompt.append("Use a").append(emailRequest.getTone()).append(" tone.");
        }

        prompt.append(emailRequest.getEmailContent());

        return prompt.toString();
    }
}
