package chat.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestSerializer {
    public static String serialize(Object requestObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestObject);
    }
}