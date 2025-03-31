package chat.service.conversation;

import chat.ExecptionHandling.Exceptions.RecordNotFoundException;
import chat.entity.conversation.ChatConversation;
import chat.repository.ChatConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatService {

    @Autowired
    private ChatConversationRepository chatConversationRepository;

    public List<ChatConversation> getAllConversations() {
        return chatConversationRepository.findAll();
    }

    public List<ChatConversation> getAllConversationsByModel(String model) {
        return chatConversationRepository.findByModel(model)
                .orElseThrow(() -> new RecordNotFoundException("Conversations for model " + model + " not found"));
    }

    public ChatConversation getConversationById(Long conversationId) {
        return chatConversationRepository.findById(conversationId)
                .orElseThrow(() -> new RecordNotFoundException("Conversation for conversationId " + conversationId + " not found"));
    }

    public ChatConversation saveConversation(ChatConversation chatConversation) {
        return chatConversationRepository.save(chatConversation);
    }
}
