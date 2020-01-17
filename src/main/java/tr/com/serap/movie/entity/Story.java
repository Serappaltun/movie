package tr.com.serap.movie.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Story")
public class Story implements Serializable {

    @Id
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("StoryName")
    private String storyName;

    @JsonProperty("Text")
    private String text;

}
