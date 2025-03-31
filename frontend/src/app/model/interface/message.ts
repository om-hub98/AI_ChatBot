export interface Message{
    messageId : number;
    message : string;
    role  : string;
    createdAt : string;
    updatedAt : string;
}

export interface ChatConversation{
    conversationId  : number;
    model : string;
    createdAt  : string;
    updatedAt  : string;
    messages  : any;
}

type APIResponseChatConversation = ChatConversation[];