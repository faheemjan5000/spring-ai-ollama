package spring.ai.ollama.spring_ai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import spring.ai.ollama.spring_ai.model.ActorsFilms;
import spring.ai.ollama.spring_ai.service.ActorFilmsService;

import java.util.List;

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

    @GetMapping("/list")
    public List<ActorsFilms> getListActorFilms(
            @RequestParam String firstActorName,
            @RequestParam String secondActorName,
            @RequestParam String thirdActorName) {
        log.info("AiActorFilmsController.getListActorFilms called with actorNames: {}, {}, {}", firstActorName, secondActorName, thirdActorName);
        return actorFilmsService.getListActorFilms(firstActorName, secondActorName, thirdActorName);
    }
}
