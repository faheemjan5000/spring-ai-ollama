package spring.ai.ollama.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OllamaService {

    @Autowired
    private ChatClient chatClient;

//    public OllamaService(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//
//    }

    public String getAnswer(String message, String conversationId) {

        log.info("OllamaService.getAnswer called with message: {}", message);
        log.info("Calling AI.....");

        String response = chatClient.prompt()
                .user(message)
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();

        log.info("response from AI {}",response);

        return response;
    }
}
