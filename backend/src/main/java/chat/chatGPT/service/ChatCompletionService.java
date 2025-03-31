package chat.chatGPT.service;

import chat.ExecptionHandling.Exceptions.GptException;
import chat.chatGPT.entity.ChatCompletionRequest;
import chat.chatGPT.feign.ChatGPTClient;
import chat.dto.ChatRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatCompletionService {

    @Value("${openai.chat.model}")
    String model;
    @Autowired
    private ChatGPTClient chatGPTClient;

    public String getChatGPTResponse(ChatRequest chatRequest) {

        ChatCompletionRequest request = new ChatCompletionRequest(model,
                List.of(new ChatCompletionRequest.Message("user", chatRequest.getMessage())));

        ObjectMapper objectMapper = new ObjectMapper();
        String responseMessage = "";
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(request);
            responseMessage = chatGPTClient.getChatCompletionResponse(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        catch (FeignException ex) {
            int statusCode = ex.status();
            throw new GptException(ex.getMessage(),statusCode);
        }
        return responseMessage;
    }
}
