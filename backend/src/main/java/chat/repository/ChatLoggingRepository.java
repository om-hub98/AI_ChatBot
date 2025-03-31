package chat.repository;


import chat.entity.conversation.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLoggingRepository extends JpaRepository<ChatLog, Long> {
}
