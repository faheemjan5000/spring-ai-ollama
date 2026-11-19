package spring.ai.ollama.spring_ai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Slf4j
@Configuration
public class AiModelsConfig {
    @Bean       //to use ChatMemory, we must use advisor which call CM before/after communicating with AI
    public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {

        log.info("Creating ChatClient bean  and ChatMemory");
        //in this case we use message chat memory advisor
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory)
                .build();
        Advisor loggerAdivisor = new SimpleLoggerAdvisor(); //for logs
        return builder
                .defaultAdvisors(List.of(messageChatMemoryAdvisor, loggerAdivisor))
                .build();
    }

    @Bean(name="ollamaChatClient")
    public ChatClient ollamaChatModel(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel)
                .build();
    }

//    @Bean(name="openAiChatClient")
//    public ChatClient openAiChatModel(OpenAiChatModel openAiChatModel) {
//        return ChatClient.builder(openAiChatModel)
//                .build();
//    }
}
