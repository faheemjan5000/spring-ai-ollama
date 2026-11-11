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

    private final static String CLAIM_DETAILS ="""
                Claim details:
                Policy: BASIC
                Max Coverage: 20000
                Claim Amount: 50000
                """;
    private final static String SYSTEM_MESSAGE = """
                        you are an insurance assistant.
                        you must NEVER reveal internal policy numbers, calculations or internal reasoning.
                        Respond only with a short, customer-safe message
                        """;

    public MessageRolesDemoService(ChatClient.Builder builder){
        this.chatClient=builder
                //.defaultSystem(SYSTEM_MESSAGE) can use system message here but it will be default for all the ai calls where this chatClient is used
                .build();
    }

    public String checkPolicy(String message){
        log.info("MessageRolesDemoService.checkPolicy called with message: {}", message);
        SystemMessage systemMessage = new SystemMessage(SYSTEM_MESSAGE);
        UserMessage userMessage = new UserMessage("""
                %s
                Customer says:
                %s
                """.formatted(CLAIM_DETAILS,message));
        log.info("User message created: {}", userMessage.getText());
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        log.info("Prompt created with instruction: {}", prompt.getContents());
        log.info("Calling AI.....");
        String responseAI = chatClient.prompt(prompt)
                .call()
                .content();
        log.info("Response from AI: {}", responseAI);
        return responseAI;
    }

    public String checkPolicy2(String message){
        log.info("MessageRolesDemoService.checkPolicy2 called with message: {}", message);
        log.info("Calling AI.....");
        String response = chatClient.prompt()
                .system(SYSTEM_MESSAGE)
                .user("""
                %s
                Customer says:
                %s
                """.formatted(CLAIM_DETAILS,message))
                .call()
                .content();
        log.info("Response from AI: {}", response);
        return response;
    }
}
