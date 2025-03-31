package chat.service.conversation;

import chat.dto.ChatRequest;
import chat.dto.ChatResponse;
import chat.dto.ErrorResponse;
import chat.entity.conversation.ChatConversation;
import chat.entity.conversation.ChatLog;
import chat.repository.ChatConversationRepository;
import chat.repository.ChatLoggingRepository;
import chat.util.RequestSerializer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatLoggingService {

    @Autowired
    private ChatLoggingRepository chatLoggingRepository;

    public void saveChatLogs(ChatRequest chatRequest, ChatResponse chatResponse, ErrorResponse errorResponse) throws Exception {
        ChatLog chatLog = new ChatLog();
        if(chatRequest!=null) {
            chatLog.setChatRequest(RequestSerializer.serialize(chatRequest));
        }
        if(chatResponse!=null) {
            chatLog.setChatResponse(RequestSerializer.serialize(chatResponse));
            chatLog.setConversationId(Long.valueOf(chatResponse.getConversationId()));
        }
        if(errorResponse!=null) {
            chatLog.setErrorResponse(RequestSerializer.serialize(errorResponse));
        }
        chatLoggingRepository.save(chatLog);
    }
}
