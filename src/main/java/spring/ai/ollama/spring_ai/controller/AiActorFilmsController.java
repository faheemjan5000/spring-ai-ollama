package spring.ai.ollama.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.ollama.spring_ai.model.ActorsFilms;


@RestController
@RequestMapping("/api/ollama/actors")
public class AiActorFilmsController {

    private ChatClient chatClient;

    public AiActorFilmsController(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    @GetMapping("/{actorName}")
    public ActorsFilms getActorFilms(@PathVariable("actorName") String actorName){
        System.out.println("Actor name recieved : "+actorName);
        ActorsFilms films = chatClient.prompt()
                .user("Generate the filmography of "+actorName)
                .call()
                .entity(ActorsFilms.class);
        System.out.println("The actor is : "+films.getActor());
        System.out.println("The films are : "+films.getMovies());
        return films;
    }
}
