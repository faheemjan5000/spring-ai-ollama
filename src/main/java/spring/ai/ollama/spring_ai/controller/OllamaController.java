package spring.ai.ollama.spring_ai.controller;

import org.springframework.web.bind.annotation.*;
import spring.ai.ollama.spring_ai.service.OllamaService;

@RestController
@RequestMapping("/ollama/demo")
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/{prompt}")
    public String getAnswer(@PathVariable String prompt,
                             @RequestParam(defaultValue = "default") String conversationId) {
        return ollamaService.getAnswer(prompt, conversationId);
    }


}
