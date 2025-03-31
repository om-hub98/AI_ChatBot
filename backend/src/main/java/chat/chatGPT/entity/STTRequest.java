package chat.chatGPT.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.util.List;

/* File and model are required. Rest are optional */
@Data
@AllArgsConstructor
public class STTRequest {

    private File file; // one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.
    private String model; // Only whisper-1 (which is powered by our open source Whisper V2 model) is currently available.
    private String language; // Optional
    private String prompt; // Optional
    private String response_format; // Optional and Defaults to 1
    private Number temperature; // Optional and Defaults to 0

}
