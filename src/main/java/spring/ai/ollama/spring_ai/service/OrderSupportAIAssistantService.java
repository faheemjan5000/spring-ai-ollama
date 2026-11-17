package spring.ai.ollama.spring_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service  //example for Prompt Template and stuffing
public class OrderSupportAIAssistantService{

    @Value("classpath:/prompts/order_system_template.st")
    private Resource orderSystemPromptResource;

    @Value("classpath:/prompts/order_user_template.st")
    private Resource orderUserPromptResource;

    @Value("classpath:/prompts/order_system_policy_template.st")
    private Resource orderPolicyPromptResource;

    private ChatClient chatClient;

    public OrderSupportAIAssistantService(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    public String assistWithOrderSupport(
            String customerName,
            String orderId,
            String customerMessage) {

        ChatOptions chatOptions = ChatOptions.builder()
                .model("ollama/qwen2.5-coder:7b")
                .build();


        return chatClient.prompt()
                .system(orderPolicyPromptResource)
                .user(promptTemplate -> promptTemplate
                        .text(orderUserPromptResource)
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();
    }
}
