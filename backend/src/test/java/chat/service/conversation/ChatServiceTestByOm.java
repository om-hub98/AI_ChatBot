//package chat.service.conversation;
//
//import chat.entity.conversation.ChatConversation;
//import chat.repository.ChatConversationRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ChatServiceTestByOm {
//    @InjectMocks
//    private ChatService chatService;
//
//    @Mock
//    private ChatConversationRepository chatConversationRepository;
//
//    @Test
//    void testMockGetAllConversations() {
//
//        // context - mock data
//        ChatConversation chatConversation1 = new ChatConversation();
//        chatConversation1.setConversationId(1l);
//        chatConversation1.setModel("GPT");
//
//        ChatConversation chatConversation2 = new ChatConversation();
//        chatConversation2.setConversationId(2l);
//        chatConversation2.setModel("LLAMA");
//
//        List<ChatConversation> mockConversations = Arrays.asList(chatConversation1,chatConversation2);
//
//        //when then
//        when(chatConversationRepository.findAll()).thenReturn(mockConversations);
//
//        //Test method
//        List<ChatConversation> result = chatService.getAllConversations();
//
//        assertEquals(2,result.size());
//        assertEquals("GPT",result.get(0).getModel());
//        verify(chatConversationRepository,times(1)).findAll();
//    }
//}
