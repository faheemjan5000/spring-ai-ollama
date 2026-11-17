package spring.ai.ollama.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Service
public class ChatOptionsExampleService {

    private ChatClient chatClient;

    public ChatOptionsExampleService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();

    }
    public Flux<String> getAnswer(String message){
        log.info("ChatOptionsExampleService.getAnswer called with message: {}", message);
        log.info("Calling AI.....");
        Flux<String> response = chatClient.prompt()
                .user(message)
                .stream()
                .content()
                .doOnNext(chunk -> log.info(chunk))
                .doOnComplete(() -> log.info("AI response completed"));
        return response;

    }
}
