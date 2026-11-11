package spring.ai.ollama.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ollama/demo")
public class OllamaController {

    private final ChatClient chatClient;

    public OllamaController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/{prompt}")
    public ResponseEntity<String> getAnswer(@PathVariable String prompt){
       var metaData = chatClient
               .prompt(prompt)
               .call()
               .chatResponse()
               .getMetadata();
        System.out.println("MetaData from Ollama: " + metaData);
       return ResponseEntity.ok(String.valueOf(metaData));
    }


}
