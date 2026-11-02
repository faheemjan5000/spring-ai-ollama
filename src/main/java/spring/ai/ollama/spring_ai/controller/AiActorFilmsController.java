package spring.ai.ollama.spring_ai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.ollama.spring_ai.model.ActorsFilms;
import spring.ai.ollama.spring_ai.service.ActorFilmsService;

@Slf4j
@RestController
@RequestMapping("/api/ollama/actors")
public class AiActorFilmsController {
    private final ActorFilmsService actorFilmsService;

    public AiActorFilmsController(
            ActorFilmsService actorFilmsService) {
        this.actorFilmsService = actorFilmsService;
    }

    @GetMapping("/{actorName}")
    public ActorsFilms getActorFilms(
            @PathVariable String actorName) {
        log.info("AiActorFilmsController.getActorFilms called with actorName: {}", actorName);
        return actorFilmsService.getActorFilms(actorName);
    }
}
