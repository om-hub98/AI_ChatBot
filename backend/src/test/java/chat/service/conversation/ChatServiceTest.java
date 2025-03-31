package chat.service.conversation;

import chat.entity.conversation.ChatConversation;
import chat.entity.conversation.Message;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(false)
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Test
    void testSaveConversation() {
        ChatConversation conversation = new ChatConversation();
        conversation.setModel("gpt-4");

        Message message1 = new Message();
        message1.setMessage("Hello!");
        message1.setRole(Message.Role.USER);

        Message message2 = new Message();
        message2.setMessage("How can I help?");
        message2.setRole(Message.Role.MODEL);

        conversation.addMessage(message1);
        conversation.addMessage(message2);

        ChatConversation savedConversation = chatService.saveConversation(conversation);

        assertNotNull(savedConversation.getConversationId());
        assertEquals(2, savedConversation.getMessages().size());
    }
}

