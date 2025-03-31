package chat.chatGPT.service;

import chat.chatGPT.entity.TTSRequest;
import chat.chatGPT.feign.ChatGPTClient;
import chat.dto.ChatRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TextToSpeechService {
    @Value("${openai.tts.model}")
    private String ttsModel;

    @Autowired
    private ChatGPTClient chatGPTClient;

    public MultipartFile convertTextToSpeech(ChatRequest chatRequest){

        TTSRequest ttsRequest = new TTSRequest();
        ttsRequest.setModel(ttsModel);
        ttsRequest.setVoice("alloy");
        ttsRequest.setInput(chatRequest.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(ttsRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        byte[] audioContent = chatGPTClient.convertTextToSpeech(requestBody);
        MultipartFile multipartFile = null;  // to be updated
        return multipartFile;
    }
}
