package chat.entity.conversation;

import chat.dto.ChatRequest;
import chat.dto.ChatResponse;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_app_logs")
@Data
public class ChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;
    @Column(name = "conversation_id")
    private Long conversationId;
    @Column(name = "chat_request")
    String chatRequest;
    @Column(name = "chat_response")
    String chatResponse;
    @Column(name = "error_response")
    String errorResponse;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
