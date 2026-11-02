package spring.ai.ollama.spring_ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorsFilms {
    private String actor;
    private List<String> movies;
}
