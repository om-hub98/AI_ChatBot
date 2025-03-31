package chat.service.conversation;

import chat.entity.conversation.ChatConversation;
import chat.repository.ChatConversationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceMockTest {

    @InjectMocks
    private ChatService chatService;

    @Mock
    private ChatConversationRepository chatConversationRepository;

    @Test
    void testGetAllConversations() {
        // Mock data
        ChatConversation conversation1 = new ChatConversation();
        conversation1.setConversationId(1L);
        conversation1.setModel("model1");

        ChatConversation conversation2 = new ChatConversation();
        conversation2.setConversationId(2L);
        conversation2.setModel("model2");

        List<ChatConversation> mockConversations = Arrays.asList(conversation1, conversation2);

        when(chatConversationRepository.findAll()).thenReturn(mockConversations);

        // Test method
        List<ChatConversation> result = chatService.getAllConversations();

        assertEquals(2, result.size());
        assertEquals("model1", result.get(0).getModel());
        verify(chatConversationRepository, times(1)).findAll();
    }

    @Test
    void testGetAllConversationsByModel() {
        // Mock data
        ChatConversation conversation = new ChatConversation();
        conversation.setConversationId(1L);
        conversation.setModel("model1");

        when(chatConversationRepository.findByModel("model1"))
                .thenReturn(Optional.of(List.of(conversation)));

        // Test method
        List<ChatConversation> result = chatService.getAllConversationsByModel("model1");

        assertEquals(1, result.size());
        assertEquals("model1", result.get(0).getModel());
        verify(chatConversationRepository, times(1)).findByModel("model1");
    }

    @Test
    void testGetConversationById() {
        // Mock data
        ChatConversation conversation = new ChatConversation();
        conversation.setConversationId(1L);
        conversation.setModel("model1");

        when(chatConversationRepository.findById(1L)).thenReturn(Optional.of(conversation));

        // Test method
        ChatConversation result = chatService.getConversationById(1L);

        assertNotNull(result);
        assertEquals("model1", result.getModel());
        verify(chatConversationRepository, times(1)).findById(1L);
    }

    @Test
    void testGetConversationById_NotFound() {
        when(chatConversationRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            chatService.getConversationById(1L);
        });

        assertEquals("Conversation not found", exception.getMessage());
        verify(chatConversationRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveConversation() {
        // Mock data
        ChatConversation conversation = new ChatConversation();
        conversation.setConversationId(1L);
        conversation.setModel("model1");

        when(chatConversationRepository.save(conversation)).thenReturn(conversation);

        // Test method
        ChatConversation result = chatService.saveConversation(conversation);

        assertNotNull(result);
        assertEquals("model1", result.getModel());
        verify(chatConversationRepository, times(1)).save(conversation);
    }
}
