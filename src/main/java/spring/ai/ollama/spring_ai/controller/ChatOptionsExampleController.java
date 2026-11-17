package spring.ai.ollama.spring_ai.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import spring.ai.ollama.spring_ai.service.ChatOptionsExampleService;

@RestController
@RequestMapping("/api/chat-options")
public class ChatOptionsExampleController {

    private ChatOptionsExampleService chatOptionsExampleService;

    public ChatOptionsExampleController(ChatOptionsExampleService chatOptionsExampleService){
        this.chatOptionsExampleService = chatOptionsExampleService;
    }

   //FOR front end @GetMapping(value = "/get-answer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   @GetMapping(value = "/get-answer")
    public Flux<String> getAnswer(@RequestParam String message){
          return chatOptionsExampleService.getAnswer(message);
    }
}
