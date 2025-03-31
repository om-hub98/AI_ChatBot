package chat.ExecptionHandling.Exceptions;

import lombok.Data;

@Data
public class GptException extends RuntimeException {

    int statusCode;

    public GptException(String message,int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
