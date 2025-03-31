package chat.chatGPT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class SpeechToTextService {
    @Value("${openai.api.key}")
    private String apiKey;
    @Value("$openai.stt.url")
    private String openaiSttUrl;

    @Autowired
    private  RestTemplate restTemplate;

    public String convertAudioToText(MultipartFile audioFile) throws IOException {
       if(audioFile == null) {
           return "No";
       }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = createRequestEntity(audioFile);

        ResponseEntity<String> response = restTemplate.exchange(
                openaiSttUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody();
    }

    private HttpEntity<MultiValueMap<String, Object>> createRequestEntity(MultipartFile audioFile) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(audioFile.getInputStream(), audioFile.getOriginalFilename()));
        body.add("model", "whisper-1");

        return new HttpEntity<>(body, getHeaders());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }
}
