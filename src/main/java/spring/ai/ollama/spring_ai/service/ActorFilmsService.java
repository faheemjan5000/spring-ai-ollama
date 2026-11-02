package spring.ai.ollama.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import spring.ai.ollama.spring_ai.model.ActorsFilms;

@Slf4j
@Service
public class ActorFilmsService {

    private final ChatClient chatClient;

    public ActorFilmsService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public ActorsFilms getActorFilms(String actorName) {
    log.info("ActorFilmsService.getActorFilms called with actorName: {}", actorName);
        ActorsFilms actorsFilms = chatClient.prompt()
                .user("Generate the filmography of " + actorName)
                .call()
                .entity(ActorsFilms.class);
        log.info("actor films got from AI : {}", actorsFilms);
        return actorsFilms;
    }
}
