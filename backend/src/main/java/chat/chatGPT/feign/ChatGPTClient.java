package chat.chatGPT.feign;

import chat.chatGPT.configuration.ChatGPTConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "chatGPTClient", url = "${openai.chat.url}" , configuration = ChatGPTConfiguration.class)
public interface ChatGPTClient {

    @PostMapping(value = "/chat/completions", consumes = "application/json")
    String getChatCompletionResponse(@RequestBody String request);

    @PostMapping(value = "/audio/speech", consumes = "application/json")
    byte[] convertTextToSpeech(@RequestBody String request);

    @PostMapping(value = "/audio/transcriptions", consumes = "application/json")
    String convertAudioToText(@RequestBody String request);

}