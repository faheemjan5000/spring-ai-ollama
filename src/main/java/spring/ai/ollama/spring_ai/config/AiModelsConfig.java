package spring.ai.ollama.spring_ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiModelsConfig {

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
