package chat.chatGPT.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/* input and model and voice are required. Rest are optional */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TTSRequest {
    private String model; // default - tts-1 or tts-1-hd
    private String input; // The maximum length is 4096 characters.
    private String voice;  // Supported voices are alloy, echo, fable, onyx, nova, and shimmer
    private String response_format; // Optional and Defaults to mp3
    private Number speed; // Optional and Defaults to 1
}
