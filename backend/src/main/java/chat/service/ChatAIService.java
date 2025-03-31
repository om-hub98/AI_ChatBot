package chat.service;

import chat.ExecptionHandling.Exceptions.GptException;
import chat.chatGPT.service.ChatCompletionService;
import chat.chatGPT.service.SpeechToTextService;
import chat.chatGPT.service.TextToSpeechService;
import chat.dto.ChatRequest;
import chat.dto.ChatResponse;
import chat.dto.ErrorResponse;
import chat.entity.conversation.ChatConversation;
import chat.entity.conversation.Message;
import chat.service.conversation.ChatLoggingService;
import chat.service.conversation.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatAIService {
    @Autowired
    ChatCompletionService chatCompletionService;
    @Autowired
    ChatLoggingService chatLoggingService;
    @Autowired
    TextToSpeechService textToSpeechService;
    @Autowired
    SpeechToTextService speechToTextService;

    @Autowired
    ChatService chatService;

    public ChatResponse getAIChatResponse(ChatRequest chatRequest) throws Exception {
        String aiName = chatRequest.getAiName() != null ? chatRequest.getAiName().toUpperCase() : "No Model Provided";
        String outputMessage = null;
        ChatConversation chatConversationDb = null;
        switch (aiName) {
            case "CHATGPT":
                try {
                    //outputMessage = chatCompletionService.getChatGPTResponse(chatRequest);
                    outputMessage = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                            "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a " +
                            "galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, " +
                            "but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the " +
                            "1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                            "and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
                    chatConversationDb = chatService.saveConversation(getChatConversationFromChatRequest(chatRequest, outputMessage));
                }
                catch (GptException ex) {
                    ErrorResponse errorResponse = new ErrorResponse("GPT: "+ ex.getMessage());
                    chatLoggingService.saveChatLogs(chatRequest,null,errorResponse);
                    throw new GptException(ex.getMessage(),ex.getStatusCode());
                }
                break;
            case "LAMA":
                break;
            default:
                outputMessage = aiName + "Will be added in the future releases";
                break;
        }

        ChatResponse chatResponse = getChatResponse(chatConversationDb, chatRequest.getMessage(), outputMessage);
        chatLoggingService.saveChatLogs(chatRequest,chatResponse,null);
        return chatResponse;
    }

    private static ChatResponse getChatResponse(ChatConversation chatConversation,String inputMessage, String outputMessage) {
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setConversationId(chatConversation.getConversationId().toString());
        chatResponse.setAiName(chatConversation.getModel());
        chatResponse.setInputMessage(inputMessage);
        chatResponse.setOutputMessage(outputMessage);
        return chatResponse;
    }

    private static ChatConversation getChatConversationFromChatRequest(ChatRequest chatRequest, String outputMessageFromModel) {
        ChatConversation chatConversation = new ChatConversation();
        Message inputMessage = new Message();
        inputMessage.setRole(Message.Role.USER);
        inputMessage.setMessage(chatRequest.getMessage());

        Message outputMessage = new Message();
        outputMessage.setRole(Message.Role.MODEL);
        outputMessage.setMessage(outputMessageFromModel);

        if(!chatRequest.getConversationId().isEmpty()) {
            chatConversation.setConversationId(Long.parseLong(chatRequest.getConversationId()));
        }

        chatConversation.setModel(chatRequest.getAiName());
        chatConversation.addMessage(inputMessage);
        chatConversation.addMessage(outputMessage);

        return chatConversation;
    }
}
