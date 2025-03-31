package chat.chatGPT.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatGPTConfiguration {

    @Value("${openai.api.key}")
    private String apiKey;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (apiKey != null && !apiKey.isEmpty()) {
                // Ensure that the Authorization header is not duplicated
                if (!requestTemplate.headers().containsKey("Authorization")) {
                    requestTemplate.header("Authorization", "Bearer " + apiKey);
                }
            } else {
                throw new IllegalStateException("OpenAI API key is not configured.");
            }
        };
    }
}
