package spring.ai.ollama.spring_ai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.ollama.spring_ai.model.Language;

import java.util.List;

import static reactor.netty.http.HttpConnectionLiveness.log;
@Slf4j
@RestController
@RequestMapping("/ollama/languages")
public class LanguageController {

    private final ChatClient chatClient;

    public LanguageController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/{prompt}")
    public ResponseEntity<List<Language>> getLanguagesWithDescFromAI(@PathVariable String prompt){
        log.info("Received original prompt: {}", prompt);
        String improvedPrompt = prompt + """
                    Return exactly with the following structure.
                    Each language must contain:
                    - title
                    - content
                    - creationYear

                    Return a JSON array.
                    """;
        log.info("Improved prompt sent to AI: {}", improvedPrompt);
        log.info("Calling AI.....");
        List<Language> response = chatClient
                .prompt()
                .user(improvedPrompt)
                .call()
                        .entity(new ParameterizedTypeReference<List<Language>>() {},
                                spec -> spec.useProviderStructuredOutput());
        log.info("Calling AI Completed");
        log.info("Languages received from AI: {}", response);
        return ResponseEntity.ok(response);
    }
}
