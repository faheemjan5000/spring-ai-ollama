package spring.ai.ollama.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import spring.ai.ollama.spring_ai.model.ActorsFilms;

import java.util.List;

@Slf4j
@Service
public class ActorFilmsService {

    private final ChatClient chatClient;

    public ActorFilmsService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public ActorsFilms getActorFilms(String actorName) {
    log.info("ActorFilmsService.getActorFilms called with actorName: {}", actorName);
    log.info("Calling AI.....");
        ActorsFilms actorsFilms = chatClient.prompt()
                .user("Generate the filmography of " + actorName)
                .call()
                .entity(ActorsFilms.class);
        log.info("actor films got from AI : {}", actorsFilms);
        return actorsFilms;
    }

    public List<ActorsFilms> getListActorFilms(String firstActorName,String secondActorName,String thirdActorName){
        String prompt = """
        Generate the filmographies for the following three actors:
        1. %s
        2. %s
        3. %s

        Return exactly three objects, one for each actor.

        IMPORTANT:
        - Return ONLY valid JSON.
        - Do not use Markdown.
        - Do not include ```json or other code fences.
        - Do not include explanations before or after the JSON.
        """.formatted(
                firstActorName,
                secondActorName,
                thirdActorName
        );
        List<ActorsFilms> films = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<ActorsFilms>>() {},spec -> spec.useProviderStructuredOutput() );

        return films;
    }
}
