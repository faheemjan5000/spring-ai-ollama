package spring.ai.ollama.spring_ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    private String title;
    private String content;
    private String creationYear;
}
