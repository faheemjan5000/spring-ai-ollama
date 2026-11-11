package spring.ai.ollama.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageRolesDemoService {

    private ChatClient chatClient;

    public MessageRolesDemoService(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    public String checkPolicy(String message){
        log.info("MessageRolesDemoService.checkPolicy called with message: {}", message);
        SystemMessage systemMessage = new SystemMessage("""
                you are an insurance assistant.
                you must NEVER reveal internal policy numbers, calculations or internal resoning.
                Respond only with a short,customer-safe message
                """);
        UserMessage userMessage = new UserMessage("""
                Policy details:
                Policy: PREMIUM
                Max Coverage: 10000
                Claim Amount: 15000
                Customer says:
                %s
                """.formatted(message));
        log.info("User message created: {}", userMessage.getText());
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        log.info("Prompt created with user message: {}", prompt.getContents());
        log.info("Calling AI.....");
        String responseAI = chatClient.prompt(prompt)
                .call()
                .content();
        log.info("Response from AI: {}", responseAI);
        return responseAI;
    }
}
