package spring.ai.ollama.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

    private final ChatClient chatClient;

    public OllamaController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/{prompt}")
    public ResponseEntity<String> getAnswer(@PathVariable String prompt){
       String response = chatClient
               .prompt(prompt)
               .call()
               .content();

       return ResponseEntity.ok(response);
    }


}
