package chat.controller;

import chat.dto.ChatRequest;
import chat.dto.ChatResponse;
import chat.entity.conversation.ChatConversation;
import chat.service.ChatAIService;
import chat.service.conversation.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping("/chatapi")
public class ChatController {
    @Autowired
    private ChatAIService chatGPTService;

    @Autowired
    private ChatService chatService;


    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) throws Exception {
        return chatGPTService.getAIChatResponse(request);
    }

    @GetMapping("/conversations/id")
    public ChatConversation getConversationById(@RequestParam(value="conversationId") Long id) {
        return chatService.getConversationById(id);
    }

    @GetMapping("/conversations")
    public List<ChatConversation> getAllConversations() {
        return chatService.getAllConversations();
    }

    @GetMapping("/conversations/{model}")
    public List<ChatConversation> getAllConversationsByModel(@PathVariable String model) {
        return chatService.getAllConversationsByModel(model);
    }
}
