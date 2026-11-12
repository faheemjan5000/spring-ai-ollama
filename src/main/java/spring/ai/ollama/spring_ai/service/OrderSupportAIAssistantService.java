package spring.ai.ollama.spring_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service  //example for Prompt Template and stuffing
public class OrderSupportAIAssistantService{

    @Value("classpath:/prompts/order_system_template.st")
    private Resource orderSystemPromptResource;

    @Value("classpath:/prompts/order_user_template.st")
    private Resource orderUserPromptResource;

    private ChatClient chatClient;

    public OrderSupportAIAssistantService(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    public String assistWithOrderSupport(
            String customerName,
            String orderId,
            String customerMessage) {

        return chatClient.prompt()
                .system(promptTemplate -> promptTemplate
                        .text(orderSystemPromptResource))
                .user(promptTemplate -> promptTemplate
                        .text(orderUserPromptResource)
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();
    }
}
